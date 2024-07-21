package org.example.telegrambot;

import org.example.telegrambot.init.BotRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TelegramBotApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext run = SpringApplication.run(TelegramBotApplication.class, args);
        BotRegister bean = run.getBean(BotRegister.class);
        bean.init();
        String property = System.getProperty("user.dir");
        System.out.println(property);
    }

}
