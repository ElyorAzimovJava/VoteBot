package org.example.telegrambot.button;

import lombok.RequiredArgsConstructor;
import org.example.telegrambot.model.VoteOptions;
import org.example.telegrambot.repository.VotesOptionsRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ButtonFactory {
    private final VotesOptionsRepository votesOptionsRepository;
    public ReplyKeyboardMarkup getAllOptions(){
        List<VoteOptions> all = votesOptionsRepository.findAll();
        ReplyKeyboardMarkup mainReplayKeyBoardMarkub = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        for (VoteOptions voteOptions : all) {
            keyboardRows.add(new KeyboardRow(List.of(new KeyboardButton(voteOptions.getName()))));
        }
        mainReplayKeyBoardMarkub.setKeyboard(keyboardRows);
        keyboardRows.add(backButton());
        mainReplayKeyBoardMarkub.setSelective(true);
        return mainReplayKeyBoardMarkub;
    }
    public ReplyKeyboardMarkup getMainMenu(){
        ReplyKeyboardMarkup vote = new ReplyKeyboardMarkup(List.of(new KeyboardRow(List.of(new KeyboardButton("BARCHA MAKTABLAR"))), new KeyboardRow(List.of(new KeyboardButton("\uD83D\uDCCA STATISTIKA"), new KeyboardButton("\uD83D\uDCDD KO'RSATMA")))));
        vote.setSelective(true);
        vote.setResizeKeyboard(true);
        return vote;
    }
    public KeyboardRow backButton (){
        return new KeyboardRow(List.of(new KeyboardButton("\uD83D\uDD19 ORQAGA")));
    }
    public ReplyKeyboardMarkup getContactButton(){
        ReplyKeyboardMarkup contactButton = new ReplyKeyboardMarkup();
        contactButton.setSelective(true);
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardButton contact = new KeyboardButton("Telefon raqamini jo'natish");
        contact.setRequestContact(true);
        keyboardRows.add(new KeyboardRow(List.of(contact)));
        keyboardRows.add(backButton());
        contactButton.setKeyboard(keyboardRows);
        contactButton.setSelective(true);
        contactButton.setResizeKeyboard(true);

        return contactButton;
    }

}
