package coder.uz.telegram_debt_bot.controller;

import coder.uz.telegram_debt_bot.database.DatabaseCon;
import coder.uz.telegram_debt_bot.model.User;
import coder.uz.telegram_debt_bot.service.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.LocalGregorianCalendar;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

public class MainController extends TelegramLongPollingBot {


    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()){
                SendMessage sendMessage;
                String text = update.getMessage().getText();
                Long chatId = update.getMessage().getChatId();
                switch (text) {
                    case "/start":
                        SendMessage buttons = ButtonsService.contactButton(chatId);
                        try {
                            execute(buttons);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "/help":
                        SendMessage sendMessage1 = new SendMessage();
                        sendMessage1.setChatId(chatId);
                        sendMessage1.setText("Gusht dukon haqida malumot /Gusht dukon \n" +
                                "Supermarket haqida malumot /Super market\n" +
                                "");
                        try {
                            execute(sendMessage1);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "\uD83E\uDD69 Gusht dukon":
                        sendMessage = MeatShopButtonService.treeButtons(chatId);
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "\uD83C\uDFE2 Super market":
                        sendMessage = ShopButtonService.shopsButtons(chatId);
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "User qushish ➕":
                        DatabaseCon databaseCon = new DatabaseCon();
                        sendMessage = new SendMessage();
                        User user = new User();
                        sendMessage.setChatId(chatId);

                        sendMessage.setText("Ism familiyasini kiriting.");
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        String text1 = message.getText();
                        user.setFullName(update.getMessage().getText());


                        sendMessage.setText("Telefon raqamini kiriting:");
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setPhoneNumber(update.getMessage().getText());


                        sendMessage.setText("Qarz miqdorini kiriting");
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setDebt(Double.parseDouble(update.getMessage().getText()));

                        user.setDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));

                        databaseCon.addUser(user, "meat");

                        break;
                    case "Orqaga ⏮":

                        break;
                    case "Qarzlar ruyhati \uD83D\uDCD6":
                        MainService mainService = new MainService();
                        sendMessage = new SendMessage();
                        sendMessage.setChatId(chatId);
                        String meat = mainService.getUserList("meat");
                        sendMessage.setText(meat);

                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }

                        break;
                    default:
                        sendMessage = new SendMessage();
                        sendMessage.setChatId(chatId);
                        sendMessage.setText("Notug'ri malumot kiritingiz /help");
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }else if (message.hasContact()){
                Contact contact = update.getMessage().getContact();
                Long chatId = message.getChatId();
                String phoneNumber = contact.getPhoneNumber();
                if (phoneNumber.equals("+998999041697") || phoneNumber.equals("998932252777")){
                    System.out.println("phoneNumber"+phoneNumber+", chatId" + chatId);
                    SendMessage sendMessage = MeatAndShopButtonService.twoButtons(chatId);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public String getBotToken() {
        return "1909460875:AAGZrr33BLapmGW9PmJtiYLcA1R7eWVygqw";
    }


    public String getBotUsername() {
        return "qarz123_app_bot";
    }
}
