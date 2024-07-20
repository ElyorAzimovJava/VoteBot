package org.example.telegrambot.init;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.telegrambot.MessageFactory;
import org.example.telegrambot.enums.UserState;
import org.example.telegrambot.model.User;
import org.example.telegrambot.service.UserService;
import org.example.telegrambot.service.VoteOptionService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetUserProfilePhotos;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class ChatBot extends TelegramLongPollingBot{
    private final UserService userService;
    private final MessageFactory messageFactory;
    private final VoteOptionService voteOptionService;
    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if(update.getMessage().hasText()){
            User user = userService.getOrAddUser(update.getMessage());
            System.out.println(user);
            if(user.getState() == UserState.START){
             execute(messageFactory.mainMenuForUser(update.getMessage().getChatId().toString()));
             user.setState(UserState.MAIN_MENU);
             userService.updateUser(user);
            }
            else if(user.getState() == UserState.MAIN_MENU){
              if(update.getMessage().getText().equals("OVOZ BERISH"))  {
                  user.setState(UserState.VOTE);
                  userService.updateUser(user);
                   execute(messageFactory.getAllOptions(update.getMessage().getChatId().toString()));

              }

            }
            else if(user.getState() == UserState.VOTE){
                if(update.getMessage().getText().equals("ORQAGA"))  {
                    SendMessage sendMessage = messageFactory.mainMenuForUser(update.getMessage().getChatId().toString());
                    sendMessage.setText("Asosiy menyu");
                    user.setState(UserState.MAIN_MENU);
                    userService.updateUser(user);
                    execute(sendMessage);
                }
                Boolean nameCorrect = voteOptionService.isNameCorrect(update.getMessage().getText());
                if(nameCorrect){
                    user.setState(UserState.CONTACT_SHARE);
                    userService.updateUser(user);
                    execute(messageFactory.vote(update.getMessage().getChatId().toString()));
                }
            }
            else if(user.getState() == UserState.CONTACT_SHARE){
                if(update.getMessage().getText().equals("ORQAGA"))  {
                    SendMessage sendMessage = messageFactory.vote(update.getMessage().getChatId().toString());
                    sendMessage.setText("Tanlang");
                    user.setState(UserState.VOTE);
                    userService.updateUser(user);
                    execute(sendMessage);
                }
            }

        }
        else if(update.getMessage().hasContact()){
            String chatId = update.getMessage().getChatId().toString();
            User userByChatId = userService.getUserByChatId(chatId);
            if(userByChatId.getIsVote()){
                SendMessage sendMessage = messageFactory.mainMenuForUser(update.getMessage().getChatId().toString());
                sendMessage.setText("Siz allaqachon ovoz bergansiz");
                userByChatId.setState(UserState.MAIN_MENU);
                userService.updateUser(userByChatId);
                execute(sendMessage);
                return;
            }
            userByChatId.setPhoneNumber(update.getMessage().getContact().getPhoneNumber());
            userByChatId.setState(UserState.MAIN_MENU);
            userByChatId.setIsVote(true);
            userService.updateUser(userByChatId);
            SendMessage sendMessage = messageFactory.mainMenuForUser(update.getMessage().getChatId().toString());
            sendMessage.setText("Ovozingiz qabul qilindi.Bizning xizmatdan foydalanganingiz uchun rahmat.");
            execute(sendMessage);

        }

    }

    @Override
    public String getBotUsername() {
        return "t.me/BekobodTumaniMaktabReytingBot.";
    }

    @Override
    public String getBotToken() {
        return "7302868514:AAFL0e0EJoagcxA1y_48fbAasC7KmqDTkcA";
    }
}
