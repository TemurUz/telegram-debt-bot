package coder.uz.telegram_debt_bot.buttons;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ShopButton {
    public static SendMessage shopsButtons(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Super market dukoniga xush kelibsiz");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardRow1 = new KeyboardRow();

        KeyboardButton keyboardButton1 = new KeyboardButton();
        keyboardButton1.setText("button1");

        keyboardRow1.add(keyboardButton1);

        KeyboardButton keyboardButton2 = new KeyboardButton();
        keyboardButton2.setText("button2");

        keyboardRow1.add(keyboardButton2);

        KeyboardRow keyboardRow2 = new KeyboardRow();

        KeyboardButton keyboardButton3 = new KeyboardButton();
        keyboardButton3.setText("button3");
        keyboardRow2.add(keyboardButton3);

        keyboard.add(keyboardRow1);
        keyboard.add(keyboardRow2);

        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;
    }
}
