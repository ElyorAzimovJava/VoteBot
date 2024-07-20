package org.example.telegrambot.repository;

import org.example.telegrambot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User,UUID> {
    Optional<User> findByChatId(String chatId);
}
