package ru.itpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.models.PhoneBookRecord;
import ru.itpark.models.User;

import java.util.List;

public interface PhoneBookRecordsRepository extends JpaRepository<PhoneBookRecord, Integer> {
    List<PhoneBookRecord> findAllByUser(User user);
}
