package coder.uz.telegram_debt_bot.controller;

import coder.uz.telegram_debt_bot.buttons.GetContactButtons;
import coder.uz.telegram_debt_bot.buttons.MeatAndShopButton;
import coder.uz.telegram_debt_bot.buttons.MeatShopButton;
import coder.uz.telegram_debt_bot.buttons.ShopButton;
import coder.uz.telegram_debt_bot.database.DatabaseCon;
import coder.uz.telegram_debt_bot.model.User;
import coder.uz.telegram_debt_bot.service.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Date;
import java.util.Calendar;

public class MainController extends TelegramLongPollingBot {

    public static String cmd = null;
    public static User user = null;

    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()){
                SendMessage sendMessage;
                String command;
                if (cmd==null){
                    command= message.getText();
                }else {
                    command = cmd;
                }
                Long chatId = update.getMessage().getChatId();
                switch (command) {
                    case "/start":
                        cmd=null;
                        SendMessage buttons = GetContactButtons.contactButton(chatId);
                        try {
                            execute(buttons);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "/help":
                        cmd=null;
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
                        cmd=null;
                        sendMessage = MeatShopButton.treeButtons(chatId);
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "\uD83C\uDFE2 Super market":
                        cmd=null;
                        sendMessage = ShopButton.shopsButtons(chatId);
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "User qushish ➕":{

                        cmd = "User qushish ➕";
                        if (user==null){
                            user = new User();
                            sendMessage = new SendMessage();
                            sendMessage.setChatId(chatId);
                            sendMessage.setText("Ism: ");
                            try {
                                execute(sendMessage);
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        }else if (user.getFullName()==null&& user.getPhoneNumber()==null&&user.getDebt()==null){
                            user.setFullName(message.getText());
                            sendMessage=new SendMessage().setChatId(chatId).setText("Phone: ");
                            try {
                                execute(sendMessage);
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        }else if (user.getPhoneNumber()==null&&user.getDebt()==null){
                            user.setPhoneNumber(message.getText());
                            sendMessage=new SendMessage().setChatId(chatId).setText("Debt: ");
                            try {
                                execute(sendMessage);
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        }else if (user.getDebt()==null){
                            user.setDebt(Double.valueOf(message.getText()));
                            user.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
                            sendMessage = new SendMessage();
                            sendMessage.setText(user.toString());
                            sendMessage.setChatId(chatId);
                            try {
                                execute(sendMessage);
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        }else if (user!=null&&user.getFullName()!=null&&user.getDate()!=null&&user.getPhoneNumber()!=null&&user.getDebt()!=null){
                            sendMessage = MeatShopButton.treeButtons(chatId);
                            try {
                                execute(sendMessage);
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        }
                    }break;
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
                if (phoneNumber.equals("+998999041697") || phoneNumber.equals("998932252777")||phoneNumber.equals("+998997214508")){
                    System.out.println("phoneNumber"+phoneNumber+", chatId" + chatId);
                    SendMessage sendMessage = MeatAndShopButton.twoButtons(chatId);
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
