package me.telfrancesco.it;

import lombok.Getter;
import me.telfrancesco.it.Commands.Ping;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Getter
public class TelegramBot {

    public String botName = "**********";
    public String botToken = "***********:****************************";

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi api = new TelegramBotsApi();
        System.out.println("Bot avviato con successo.");

        try {
            api.registerBot(new Ping());
            System.out.println("Modulo Ping abilitato");
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}