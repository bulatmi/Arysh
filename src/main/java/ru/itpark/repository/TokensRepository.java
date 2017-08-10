package ru.itpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.models.Token;

public interface TokensRepository extends JpaRepository<Token, Integer> {
}
