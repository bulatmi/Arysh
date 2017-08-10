package ru.itpark.services;

import ru.itpark.dto.PhoneRecordDto;
import ru.itpark.dto.UserDto;
import ru.itpark.dto.UserRegistrationDto;
import ru.itpark.models.PhoneBookRecord;
import ru.itpark.models.Token;
import ru.itpark.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itpark.repository.PhoneBookRecordsRepository;
import ru.itpark.repository.TokensRepository;
import ru.itpark.repository.UsersRepository;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// аннотация Service синоним аннотации Component
// она позволяет спрингу поместить экзепляр класса
// UsersServiceImpl в поле класса-контроллера
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TokensRepository tokensRepository;

    @Autowired
    private PhoneBookRecordsRepository phoneBookRecordsRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private SecureRandom random = new SecureRandom();

    @Override
    public UserDto registration(UserRegistrationDto user) {
        // 1. Хешировать пароль
        String hashPassword = encoder.encode(user.getPassword());
        // 2. Сохранить в БД
        User model = new User(user.getName(), user.getLogin(), hashPassword);
        usersRepository.save(model);
        // 3. Получить id и имя и вернуть как результат
        return new UserDto(model.getId(), model.getName());
    }

    @Override
    public String login(String login, String password) {
        // найти пользователя по логину
        User user = usersRepository.findFirstByLogin(login);
        if (user != null &&
                encoder.matches(password, user.getHashPassword())) {
            // генерируем случайную строку - токен
            String token = new BigInteger(130, random).toString(32);
            // добавили пользователю данный токен
            Token tokenModel = new Token(token, LocalDateTime.now(), user);
            // добавили всю новую информацию в бд
            tokensRepository.save(tokenModel);
            // вернули токен
            return token;
        } else throw new IllegalArgumentException("User not found");
    }

    @Override
    public List<PhoneRecordDto> getRecords(String token) {
        User user = usersRepository.findOneByToken(token);
        List<PhoneBookRecord> records = phoneBookRecordsRepository.findAllByUser(user);
        // records - список записей-моделей
        // stream() - метод получения потока данных из исходного списка
        // map(F f) - применение функции f к каждой записи исходного стрима
        // f = record -> new Phone.. - преобразование каждой записи
        // исходого стрима в объект Dto
        // collect - функция сборки стрима в обычный список
        return records
                .stream().map(record ->
                        new PhoneRecordDto(
                                record.getName(),
                                record.getPhone()))
                .collect(Collectors.toList());
    }

    @Override
    public PhoneRecordDto addRecord(String token, PhoneRecordDto record) {
        User user = usersRepository.findOneByToken(token);
        PhoneBookRecord newRecord = new
                PhoneBookRecord(record.getName(), record.getPhone(), user);
        phoneBookRecordsRepository.save(newRecord);
        return record;

    }
}
