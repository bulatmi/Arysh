package ru.itpark.services;

import ru.itpark.dto.PhoneRecordDto;
import ru.itpark.dto.UserDto;
import ru.itpark.dto.UserRegistrationDto;

import java.util.List;

public interface UsersService {
    UserDto registration(UserRegistrationDto user);

    String login(String login, String password);

    List<PhoneRecordDto> getRecords(String token);

    PhoneRecordDto addRecord(String token, PhoneRecordDto record);
}
