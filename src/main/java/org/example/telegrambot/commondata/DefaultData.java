package org.example.telegrambot.commondata;

import lombok.RequiredArgsConstructor;
import org.example.telegrambot.model.VoteOptions;
import org.example.telegrambot.repository.VotesOptionsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.comments.CommentLine;

@Component
@RequiredArgsConstructor
public class DefaultData implements CommandLineRunner {
    private final VotesOptionsRepository votesOptionsRepository;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;
    @Override
    public void run(String... args) throws Exception {
        if(ddlAuto.equals("create")) {
            VoteOptions sixSchool = new VoteOptions("Bekobod tumani 6-maktab", 432);
            VoteOptions fourSchool = new VoteOptions("Bekobod tumani 4-maktab", 543);
            VoteOptions OneSchool = new VoteOptions("Bekobod tumani 1-maktab", 621);
            VoteOptions FiftyFourSchool = new VoteOptions("Bekobod tumani 44-maktab", 126);
            VoteOptions FiftySchool = new VoteOptions("Bekobod tumani 40-maktab", 347);
            votesOptionsRepository.save(sixSchool);
            votesOptionsRepository.save(fourSchool);
            votesOptionsRepository.save(OneSchool);
            votesOptionsRepository.save(FiftySchool);
            votesOptionsRepository.save(FiftyFourSchool);
        }
    }
}
