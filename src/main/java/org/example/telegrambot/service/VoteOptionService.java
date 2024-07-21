package org.example.telegrambot.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.telegrambot.repository.VotesOptionsRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteOptionService  {
    private final VotesOptionsRepository votesOptionsRepository;
    public Boolean isNameCorrect(String name){
         return votesOptionsRepository.existsByName(name);
    }
    @Transactional
    public void increaseVoteCount(String name){
         votesOptionsRepository.increaseCount(name);
    }
}
