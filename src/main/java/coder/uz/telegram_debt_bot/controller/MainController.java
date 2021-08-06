package coder.uz.telegram_debt_bot.controller;

import coder.uz.telegram_debt_bot.buttons.*;
import coder.uz.telegram_debt_bot.database.DatabaseCon;
import coder.uz.telegram_debt_bot.model.User;
import coder.uz.telegram_debt_bot.service.*;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.*;
import java.util.Calendar;

public class MainController extends TelegramLongPollingBot {

    private String url = "jdbc:postgresql://localhost:5432/debt_db";
    private String userName = "postgres";
    private String password = "9704";
    Connection connection = null;

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, userName, password);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public String getBotToken() {
        return "1909460875:AAGZrr33BLapmGW9PmJtiYLcA1R7eWVygqw";
    }


    public String getBotUsername() {
        return "qarz123_app_bot";
    }

    public static String cmd = null;
    private User user = null;
    private int step = 0;
    private MainService mainService;
    private DatabaseCon databaseCon;

     @SneakyThrows
    public void onUpdateReceived(Update update) {

        SendMessage sendMessage = new SendMessage();
        Message message = update.getMessage();
        String receivedMessage = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        String sendingMessage = "111";

        if (update.hasMessage()) {
            if (message.hasText()) {

                if (receivedMessage.equals("/start")) {
                    step = 1;
                }else if (receivedMessage.equals("\uD83E\uDD69 Gusht dukon")) {
                    step = 2;
                }else if (receivedMessage.equals("Orqaga ⏮")){
                    step = 3;
                }else if (receivedMessage.equals("User qushish ➕")){
                    step = 4;
                }else if (receivedMessage.equals("Qarzlar ruyhati \uD83D\uDCD6")){
                    step = 5;
                }else if (receivedMessage.startsWith("/todo_edit")){
                    step = 6;
                    cmd = receivedMessage;
                }else if (receivedMessage.equals("Orqaga \uD83D\uDD19")){
                    step = 2;
                }else if (receivedMessage.equals("Delete \uD83D\uDDD1")){
                    step = 7;
                }else if (receivedMessage.equals("Update \uD83D\uDD04")){
                    step = 12;
                }

                switch (step){
                    case 1:
                        sendingMessage = "Assalomu alaykum Botga hush kelibsiz";
                        ReplyKeyboardMarkup replyKeyboardMarkup = GetContactButtons.contactButton();
                        sendMessage.setReplyMarkup(replyKeyboardMarkup);
                        break;
                    case 2:
                        sendingMessage = "Gusht dukonchasiga hush kelibsiz";
                        sendMessage.setReplyMarkup(MeatShopButton.treeButtons());
                        break;
                    case 3:
                        MeatAndShopButton.twoButtons(chatId);
                        sendingMessage = "Marketlardan birini tanlang";
                        sendMessage.setReplyMarkup(MeatAndShopButton.twoButtons(chatId));
                        break;
                    case 4:
                        // add user
                        if (user==null){
                            user = new User();
                            sendingMessage = "Ismini kiriting";
                        }else if (user.getFullName()==null&& user.getPhoneNumber()==null&&user.getDebt()==null){
                            if (receivedMessage.equals("Orqaga ⏮")){
                                user = null;
                            }else {
                                user.setFullName(message.getText());
                            }

                            sendingMessage = "Phone: ";
                        }else if (user.getPhoneNumber()==null&&user.getDebt()==null){
                            user.setPhoneNumber(message.getText());
                            sendingMessage = "Debt: ";
                        }else if (user.getDebt()==null){
                            Double debt = Double.parseDouble(message.getText()+".0");

                            user.setDebt(debt);
                            user.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
                            String query = "INSERT INTO " + " meat " + "(\"fullName\", date , \"phoneNumber\",debt)" +
                                    "VALUES (?,?,?,?)";
                            PreparedStatement preparedStatement = null;
                            preparedStatement = getConnection().prepareStatement(query);
                            preparedStatement.setString(1, user.getFullName());
                            preparedStatement.setDate(2, user.getDate());
                            preparedStatement.setString(3, user.getPhoneNumber());
                            preparedStatement.setDouble(4, user.getDebt());
                            preparedStatement.execute();
                            user=null;
                            sendingMessage = "Data successfully saved!";

                        }
                        break;
                    case 5:
                        //debt list
                        mainService = new MainService();
                        sendingMessage = mainService.getUserList("meat");
                        break;
                    case 6:
                        //todo_edit function
                        sendMessage.setReplyMarkup(UpdateAndDeleteButton.userUpdateAndDeleteButton());
                        mainService = new MainService();
                        String meat = mainService.getUser(receivedMessage, "meat");
                        sendingMessage = meat;
                        break;
                    case 7:
                        //Delete meat shops
                        mainService = new MainService();
//                        databaseCon = new DatabaseCon();
                        int id = Integer.parseInt(mainService.userId(cmd, "meat"));
                        String queryDelete = "DELETE FROM  meat WHERE id=?";
                        PreparedStatement preparedStatement = null;
                        try {
                            preparedStatement = getConnection().prepareStatement(queryDelete);
                            preparedStatement.setInt(1, id);
                            preparedStatement.execute();
                            sendingMessage = "muvofiqiyatli o'chirildi user";

                        } catch (SQLException throwables) {
                            sendingMessage = "bunaqa user id mavjud emas";
                            throwables.printStackTrace();
                        }
                        step = 6;
                        break;
                    case 12:
                        mainService = new MainService();
                        String userId = mainService.userId(cmd, "meat");
                        Long usId = Long.parseLong(userId);
                        if (receivedMessage.equals("Update \uD83D\uDD04")) {
                            user = new User();
                            receivedMessage = null;
                        }
                        if ((user.getId() == null) && receivedMessage==null){
                            if (userId==null){
                                sendingMessage = "Bunaqa user yuq";
                            }else {
                                user.setId(usId);
                                sendingMessage = "full nameni kirit";
                                System.out.println(user);
                                receivedMessage = null;
                            }
                        }else if (user.getFullName() == null&&user.getPhoneNumber()==null && user.getDebt()==null) {
                            user.setFullName(receivedMessage);
                            sendingMessage = "phone number kiriting:";
                            receivedMessage = null;
                        } else if (user.getPhoneNumber() == null && user.getDebt() == null) {
                            user.setPhoneNumber(receivedMessage);
                            sendingMessage = "qarzini kiriting";
                        }else if (user.getDebt()==null){
                            user.setDebt(Double.parseDouble(receivedMessage));
                            user.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
                            String query = "Update meat set meat.fullName= ? , date=?, meat.phoneNumber=?, debt=? where  id=" + user.getId() + " ;";
                            try {
                                PreparedStatement preparedStatement1 = getConnection().prepareStatement(query);
                                preparedStatement1.setString(1, user.getFullName());
                                preparedStatement1.setDate(2, user.getDate());
                                preparedStatement1.setString(3,user.getPhoneNumber());
                                preparedStatement1.setDouble(4,user.getDebt());
                                System.out.println(user);
                                preparedStatement1.execute();
                                sendingMessage = "muvofiqiatli o'zgartirildi ";
                                user = null;
                                receivedMessage = null;
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                            break;
                    default:
                        sendingMessage="Notug'ri malumot";
                        break;
                }
//                step++;
            } else if (message.hasContact()) {
                Contact contact = update.getMessage().getContact();
                String phoneNumber = contact.getPhoneNumber();
                if (phoneNumber.equals("+998999041697")
                        || phoneNumber.equals("+998932252777")
                        || phoneNumber.equals("+998997214508")
                        || phoneNumber.equals("998939046601")
                        || phoneNumber.equals("905527410197")
                ) {
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