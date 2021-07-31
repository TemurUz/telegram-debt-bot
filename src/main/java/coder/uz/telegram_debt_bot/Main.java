package coder.uz.telegram_debt_bot;

import coder.uz.telegram_debt_bot.controller.MainController;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;

public class Main {
    public static void main(String[] args) {
        ApiContextInitializer.init();

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            telegramBotsApi.registerBot(new MainController());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}
