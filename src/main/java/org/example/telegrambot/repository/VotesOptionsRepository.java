package org.example.telegrambot.repository;

import org.example.telegrambot.model.VoteOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface VotesOptionsRepository extends JpaRepository< VoteOptions,UUID> {
    Boolean existsByName(String name);
    @Query(value = "update VoteOptions v set v.countOfVotes=v.countOfVotes+1 where v.name=:name")
    @Modifying
    void increaseCount(@Param("name") String name);

}
