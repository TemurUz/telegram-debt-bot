package coder.uz.telegram_debt_bot.service;

import coder.uz.telegram_debt_bot.controller.MainController;
import coder.uz.telegram_debt_bot.database.DatabaseCon;
import coder.uz.telegram_debt_bot.model.User;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public class MainService {
    DatabaseCon databaseCon = new DatabaseCon();
    List<User> list;
    public String getUserList(String tableName){
        list = databaseCon.getUser(tableName);
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

    public String getUser(String text, String tableName){
        String d = "Bunday user mavjud emas";
        int count = 1;
        List<User> userList = databaseCon.getUser(tableName);
        for (User user : userList) {
            if (user.getId().toString().equals(text.substring(10))) {
                d = count + ")\uD83D\uDC64 " + user.getFullName() + "\n" +
                        "   \uD83D\uDCF1 " + user.getPhoneNumber() + "\n   \uD83D\uDCB0 " + user.getDebt() + " SO'M\n" +
                        "   \uD83D\uDCC5 "+user.getDate()+ "\n" ;

            }
            count++;
        }
        return d;
    }

    public String userId(String text, String tableName){
        String res = null;
       List<User> userList1 = databaseCon.getUser(tableName);
        for (User user : userList1) {
            if (user.getId().toString().equals(text.substring(10))){
                res = text.substring(10);
            }
        }
        return res;
    }

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
