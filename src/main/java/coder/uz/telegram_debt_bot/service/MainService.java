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
        int count = 1;
        String result = "";
        for (User user : list) {
            result += count + ")\uD83D\uDC64 " + user.getFullName() + "\n" +
                    "   \uD83D\uDCF1 " + user.getPhoneNumber() + "\n   \uD83D\uDCB0 " + user.getDebt() + " SO'M\n" +
                    "   \uD83D\uDCC5 "+user.getDate()+ "\n" +
                    "/todo_edit"+user.getId()+
                    "  ---------------------\n";
            count++;
        }
        return result;
    }

 MainController mainController = new MainController();

//    public String addUser(User user, Message message) {
//        String receivedMessage = "";
//        user.getFullName();
////        user.getPhoneNumber();
//        user.getDebt();
//
//        if (user == null && message.getText().equals("User qushish ➕")){
//            receivedMessage = "Ismini kiriting";
//            return receivedMessage;
//        }else if (user.getFullName()==null&& user.getPhoneNumber()==null&&user.getDebt()==null) {
//            user.setFullName(message.getText());
//            receivedMessage = "Phone numberini kiriting";
//            return receivedMessage;
//        }
//        return "";
//    }



}
