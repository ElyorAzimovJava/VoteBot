package org.example.telegrambot;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.telegrambot.button.ButtonFactory;
import org.example.telegrambot.model.VoteOptions;
import org.example.telegrambot.repository.VotesOptionsRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

@Component
@RequiredArgsConstructor

public class MessageFactory {
     private final ButtonFactory buttonFactory;
     private final VotesOptionsRepository votesOptionsRepository;
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
    public SendMessage sendStatistic(String chatId){
        StringBuilder  testOfStatistic  = new StringBuilder("---------------STATISTIKA------------- \n");
        List<VoteOptions> all = votesOptionsRepository.findAll();
        for (int i = 0; i < all.size(); i++) {
            testOfStatistic.append(i + 1 + ")" + all.get(i).getName() + "    " + all.get(i).getCountOfVotes() + " ta ovoz \n" );
        }
        return new SendMessage(chatId,testOfStatistic.toString());

    }
    public SendMessage instruction(String chatId){
        String instructionMessage = """
                Bekobod tumanidagi bir nechta maktablar reytingni aniqlash uchun yaratilgan botdan ovoz berish quyidagilardan iborat
                1) /Start tugmasini bosish va bot bilan chatni boshlash
                2)Asosiy menyudan 'BARCHA MAKTABLAR '  bo'limini tanlash;
                3) Ro'yxatdagi maktablardan birini tanlash;
                4) Telefon raqami orqali ovoz berish(Agar bu telegram account orqali oldin ovoz bergan bo'lsangiz inobatga olinmaydi)
                Qo'shimcha ko'rsatmalar
                1) Asosiy menyudan STATISTIKA bo'limidan reytingi kuzatishingiz mumkin.
                2) ORQAGA tugmasi orqali bitta oldingi menuga o'tishingiz mumkin,
                
                """;
       return new SendMessage(chatId,instructionMessage);
    }
}
