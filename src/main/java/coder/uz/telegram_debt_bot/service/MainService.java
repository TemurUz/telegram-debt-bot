package coder.uz.telegram_debt_bot.service;

import coder.uz.telegram_debt_bot.buttons.MeatShopButton;
import coder.uz.telegram_debt_bot.controller.MainController;
import coder.uz.telegram_debt_bot.database.DatabaseCon;
import coder.uz.telegram_debt_bot.model.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;



public class MainService {
    DatabaseCon databaseCon = new DatabaseCon();

    public String getUserList(String tableName){
        List<User> list = databaseCon.getUser(tableName);
        String result = "";
        for (User user : list) {
            result += user.getId() + ")\uD83D\uDC64 " + user.getFullName() + "\n" +
                    "   \uD83D\uDCF1 " + user.getPhoneNumber() + "\n   \uD83D\uDCB0 " + user.getDebt() + " SO'M\n" +
                    "   \uD83D\uDCC5 "+user.getDate()+ "\n  ---------------------\n";
        }
        return result;
    }

 MainController mainController = new MainController();

//    public void addUser(User user, Long chatId, Message message, Update update) {
//        SendMessage sendMessage;
//
//        if (user == null) {
//            user = new User();
//            sendMessage = new SendMessage();
//            sendMessage.setChatId(chatId);
//            sendMessage.setText("Ism: ");
//            mainController.executeMethod(sendMessage);
////            try {
////                execute(sendMessage);
////            } catch (TelegramApiException e) {
////                e.printStackTrace();
////            }
//        } else if (user.getFullName() == null && user.getPhoneNumber() == null && user.getDebt() == null) {
//            user.setFullName(message.getText());
//            sendMessage = new SendMessage().setChatId(chatId).setText("Phone: ");
//            mainController.executeMethod(sendMessage);
////            try {
////                execute(sendMessage);
////            } catch (TelegramApiException e) {
////                e.printStackTrace();
////            }
//        } else if (user.getPhoneNumber() == null && user.getDebt() == null) {
//            user.setPhoneNumber(message.getText());
//            sendMessage = new SendMessage().setChatId(chatId).setText("Debt: ");
//            mainController.executeMethod(sendMessage);
//
////            try {
////                execute(sendMessage);
////            } catch (TelegramApiException e) {
////                e.printStackTrace();
////            }
//        } else if (user.getDebt() == null) {
//            user.setDebt(Double.valueOf(message.getText()));
//            user.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
//            sendMessage = new SendMessage();
//            sendMessage.setText(user.toString());
//            sendMessage.setChatId(chatId);
//            mainController.executeMethod(sendMessage);
////            try {
////                execute(sendMessage);
////            } catch (TelegramApiException e) {
////                e.printStackTrace();
////            }
//            DatabaseCon databaseCon = new DatabaseCon();
//            databaseCon.addUser(user, "meat");
//            System.out.println(user);
//        } else if (user != null && user.getFullName() != null && user.getDate() != null && user.getPhoneNumber() != null && user.getDebt() != null) {
//
//            user = null;
//            sendMessage = MeatShopButton.treeButtons(chatId);
//            mainController.executeMethod(sendMessage);
////            try {
////                execute(sendMessage);
////            } catch (TelegramApiException e) {
////                e.printStackTrace();
////            }
//        }
//    }



}
