package org.example.telegrambot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VoteOptions extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String name;
    private Integer countOfVotes;
}
