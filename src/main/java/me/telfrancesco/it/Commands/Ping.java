package me.telfrancesco.it.Commands;

import me.telfrancesco.it.TelegramBot;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ping extends TelegramLongPollingBot {

    TelegramBot telegramBot = new TelegramBot();
    String line;

    public void onUpdateReceived(Update update) {

        String msg = update.getMessage().getText();
        String[] args = msg.split(" ");
        String chatId = update.getMessage().getChatId().toString();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        if (args.length == 1 && msg.startsWith("/ping")) {
            sendMessage.setText("Inserisci argomenti validi!");
        } else {
            try {
                Process process = Runtime.getRuntime().exec("ping " + args[1] + " -c 1");
                StringBuilder output = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                while ((line = reader.readLine()) != null)
                    output.append(line).append("\n");

                sendMessage.setText(String.valueOf(output));
                execute(sendMessage);
            } catch (IOException | TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public String getBotUsername() {
        return telegramBot.getBotName();
    }

    @Override
    public String getBotToken() {
        return telegramBot.getBotToken();
    }
}
