package org.example.telegrambot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.example.telegrambot.enums.UserState;
import org.springframework.data.domain.PageRequest;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String chatId;
    @Column(unique = true)
    private String username;
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private UserState state;
    private Boolean isAdmin;
    private String phoneNumber;
    private Boolean isVote;
    private String votedSchoolName;
}
