package coder.uz.telegram_debt_bot.service;

import coder.uz.telegram_debt_bot.database.DatabaseCon;
import coder.uz.telegram_debt_bot.model.User;

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
}
