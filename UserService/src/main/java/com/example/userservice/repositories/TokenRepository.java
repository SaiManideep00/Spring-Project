package com.example.userservice.repositories;

import com.example.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Long> {
    Optional<Token> findByValueAndDeleted(String tokenValue, boolean deleted);

    Optional<Token> findByValueAndDeletedAndExpiryAtGreaterThan(String token, boolean deleted,LocalDate localDate);
}