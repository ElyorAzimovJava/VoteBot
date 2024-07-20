package org.example.telegrambot.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.telegrambot.dto.user.UserRequestDto;
import org.example.telegrambot.model.User;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class UserMapperClass {
    public User mapToUser(UserRequestDto userRequestDto){
        User user = new User();
        user.setChatId(userRequestDto.getChatId());
        user.setUsername(user.getUsername());
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        return user;
    }
}
