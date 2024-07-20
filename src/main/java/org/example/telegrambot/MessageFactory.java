package org.example.telegrambot;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.telegrambot.button.ButtonFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@RequiredArgsConstructor

public class MessageFactory {
     private final ButtonFactory buttonFactory;
    public  SendMessage mainMenuForUser(String chatId){
        SendMessage mainMenu = new SendMessage(chatId, "Assalomu alaykum.Bekobod tumanida o'tkazilayotgan maktab o'quvchilar o'rtasidagi bilim darajasini aniqlash maqsadida o'tkazilayotgan so'rovnomamizda qatnashish istagini bildirganizgiz uchun rahmat.Ovoz berish tugmasini tanlang belgilangan maktablardan biriga ovoz bering");
        mainMenu.setReplyMarkup(buttonFactory.getMainMenu());
        return mainMenu;
    }
    public SendMessage getAllOptions(String chatId){
        SendMessage allOptions = new SendMessage(chatId, "Barcha Maktablar ro'yxati");
        allOptions.setReplyMarkup(buttonFactory.getAllOptions());
        return allOptions;
    }
    public SendMessage vote(String chatId){
        SendMessage sendMessage = new SendMessage(chatId, " Agar oldin bu nomerdan ovoz bermagan bo'lsangiz iltimos telefon raqamingizni jonatib ovoz berishni davom ettiring. ");
        sendMessage.setReplyMarkup(buttonFactory.getContactButton());
        return sendMessage;
    }
}
