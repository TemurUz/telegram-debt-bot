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
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Date;
import java.util.Calendar;

public class MainController extends TelegramLongPollingBot {

    public String getBotToken() {
        return "1909460875:AAGZrr33BLapmGW9PmJtiYLcA1R7eWVygqw";
    }


    public String getBotUsername() {
        return "qarz123_app_bot";
    }

    public static String cmd = null;
    public static User user = null;
    public int step = 0;

    public void onUpdateReceived(Update update) {
//        if (update.hasMessage()) {
//            Message message = update.getMessage();
//            if (message.hasText()){
//                SendMessage sendMessage;
//                String command;
//                if (cmd==null){
//                    command= message.getText();
//                }else {
//                    command = cmd;
//                }
//                command = command.toLowerCase();
////                if (command.contains("/start")){
////                    step = 1;
////                }
//
//                if (command.contains("\uD83E\uDD69 Gusht dukon")){
//
//                }
//
//                Long chatId = update.getMessage().getChatId();
//                switch (command) {
//                    case "/start":
//                        cmd=null;
//                        SendMessage buttons = GetContactButtons.contactButton(chatId);
//                        try {
//                            execute(buttons);
//                        } catch (TelegramApiException e) {
//                            e.printStackTrace();
//                        }
//                        break;
//
//                    case "\uD83E\uDD69 Gusht dukon":
//                        cmd=null;
//                        sendMessage = MeatShopButton.treeButtons(chatId);
//                        try {
//                            execute(sendMessage);
//                        } catch (TelegramApiException e) {
//                            e.printStackTrace();
//                        }
//                        break;
//
//                    case "\uD83C\uDFE2 Super market":
//                        cmd=null;
//                        sendMessage = ShopButton.shopsButtons(chatId);
//                        try {
//                            execute(sendMessage);
//                        } catch (TelegramApiException e) {
//                            e.printStackTrace();
//                        }
//                        break;
//                    case "User qushish ➕":{
//
//                        cmd = "User qushish ➕";
//
//                        if (user==null){
//                            user = new User();
//                            sendMessage = new SendMessage();
//                            sendMessage.setChatId(chatId);
//                            sendMessage.setText("Ism: ");
//                            try {
//                                execute(sendMessage);
//                            } catch (TelegramApiException e) {
//                                e.printStackTrace();
//                            }
//                        }else if (user.getFullName()==null&& user.getPhoneNumber()==null&&user.getDebt()==null){
//                            user.setFullName(message.getText());
//                            sendMessage=new SendMessage().setChatId(chatId).setText("Phone: ");
//                            try {
//                                execute(sendMessage);
//                            } catch (TelegramApiException e) {
//                                e.printStackTrace();
//                            }
//                        }else if (user.getPhoneNumber()==null&&user.getDebt()==null){
//                            user.setPhoneNumber(message.getText());
//                            sendMessage=new SendMessage().setChatId(chatId).setText("Debt: ");
//                            try {
//                                execute(sendMessage);
//                            } catch (TelegramApiException e) {
//                                e.printStackTrace();
//                            }
//                        }else if (user.getDebt()==null){
//                            user.setDebt(Double.valueOf(message.getText()));
//                            user.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
//
//                            sendMessage = new SendMessage();
////                            sendMessage.setText(user.toString());
//                            DatabaseCon databaseCon = new DatabaseCon();
//                            databaseCon.addUser(user, "meat");
////                            sendMessage = MeatShopButton.treeButtons(chatId);
//                            sendMessage.setText(message.getContact().getPhoneNumber());
//                            sendMessage.setChatId(chatId);
//                            try {
//                                execute(sendMessage);
//                            } catch (TelegramApiException e) {
//                                e.printStackTrace();
//                            }
//
//                            cmd=null;
//                            user = null;
//                            System.out.println(user);
//                        }
//
////                        else if (user!=null&&user.getFullName()!=null&&user.getDate()!=null&&user.getPhoneNumber()!=null&&user.getDebt()!=null){
////                            sendMessage = MeatShopButton.treeButtons(chatId);
////                            user=null;
////                            cmd  = null;
////                            try {
////                                execute(sendMessage);
////                            } catch (TelegramApiException e) {
////                                e.printStackTrace();
////                            }
////                        }
////
//
//                    }break;
//
//                    case "Orqaga ⏮":
//                        sendMessage = MeatAndShopButton.twoButtons(chatId);
//                        executeMethod(sendMessage);
//                        break;
//                    case "Qarzlar ruyhati \uD83D\uDCD6":
//                        MainService mainService = new MainService();
//                        sendMessage = new SendMessage();
//                        sendMessage.setChatId(chatId);
//                        String meat = mainService.getUserList("meat");
//                        sendMessage.setText(meat);
//
//                        try {
//                            execute(sendMessage);
//                        } catch (TelegramApiException e) {
//                            e.printStackTrace();
//                        }
//
//                        break;
//                    default:
//                        sendMessage = new SendMessage();
//                        sendMessage.setChatId(chatId);
//                        sendMessage.setText("Notug'ri malumot kiritingiz /help");
//                        try {
//                            execute(sendMessage);
//                        } catch (TelegramApiException e) {
//                            e.printStackTrace();
//                        }
//                        break;
//                }
//            }else if (message.hasContact()){
//                Contact contact = update.getMessage().getContact();
//                Long chatId = message.getChatId();
//                String phoneNumber = contact.getPhoneNumber();
//                if (phoneNumber.equals("+998999041697") || phoneNumber.equals("+998932252777")||phoneNumber.equals("+998997214508") || phoneNumber.equals("998939046601")){
//                    System.out.println("phoneNumber"+phoneNumber+", chatId" + chatId);
//                    SendMessage sendMessage = MeatAndShopButton.twoButtons(chatId);
//                    try {
//                        execute(sendMessage);
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }


        SendMessage sendMessage = new SendMessage();
        Message message = update.getMessage();
        String receivedMessage = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        String sendingMessage = "";

        if (update.hasMessage()) {
            if (message.hasText()) {
                if (receivedMessage.equals("/start")) {
                    step = 1;
                }
                if (receivedMessage.equals("\uD83E\uDD69 Gusht dukon")) {
                    step = 2;
                }
                if (receivedMessage.equals("Orqaga ⏮")) {
                    step -= 1;
                }

                switch (step) {
                    case 1:
                        sendingMessage = "Assalomu alaykum Botga hush kelibsiz";
                        ReplyKeyboardMarkup replyKeyboardMarkup = GetContactButtons.contactButton();
                        sendMessage.setReplyMarkup(replyKeyboardMarkup);
                        break;
                    case 2:

                        break;
                    case 3:
                        sendingMessage = "Gusht dukonchasiga hush kelibsiz";
                        sendMessage.setReplyMarkup(MeatShopButton.treeButtons());
                        break;
                    case 4:

                        break;
                }
//                step++;
            } else if (message.hasContact()) {
                Contact contact = update.getMessage().getContact();
                String phoneNumber = contact.getPhoneNumber();
                if (phoneNumber.equals("+998999041697")
                        || phoneNumber.equals("+998932252777")
                        || phoneNumber.equals("+998997214508")
                        || phoneNumber.equals("998939046601")) {
                    System.out.println("phoneNumber" + phoneNumber + ", chatId" + chatId);
                    ReplyKeyboardMarkup replyKeyboardMarkup = MeatAndShopButton.twoButtons(chatId);
                    sendingMessage = "Marketlardan birini tanlang";
                    sendMessage.setReplyMarkup(replyKeyboardMarkup);
                }
            }


            sendMessage.setText(sendingMessage);
            sendMessage.setChatId(chatId.toString());
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}