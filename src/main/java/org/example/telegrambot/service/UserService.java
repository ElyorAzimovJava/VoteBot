package org.example.telegrambot.service;

import lombok.RequiredArgsConstructor;
import org.example.telegrambot.enums.UserState;
import org.example.telegrambot.mapper.UserMapperClass;
import org.example.telegrambot.model.User;
import org.example.telegrambot.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapperClass userMapperClass;
    public User getOrAddUser(Message message){
        org.telegram.telegrambots.meta.api.objects.User from = message.getFrom();
        Optional<User> byChatId = userRepository.findByChatId(from.getId().toString());
        if(byChatId.isPresent()){
            return byChatId.get();
        }
        else {
            User newUser = new User(from.getId().toString(), from.getUserName(), from.getFirstName(),
                    from.getLastName(), UserState.START, true,null,false);
            userRepository.save(newUser);
            return newUser;
        }
    }
    public User getUserByChatId(String chatId){
       return userRepository.findByChatId(chatId).orElseThrow(
                () -> new RuntimeException("User bu chat Id bilan topilmadi")
        );

    }
    public void updateUser(User user){
        userRepository.save(user);
    }

}
