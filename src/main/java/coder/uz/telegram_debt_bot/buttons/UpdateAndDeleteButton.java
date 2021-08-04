package coder.uz.telegram_debt_bot.buttons;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class UpdateAndDeleteButton {

    public static InlineKeyboardMarkup inlineButton(){
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        //update fullName button
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Update fullName");
        inlineKeyboardButton1.setCallbackData("update name");
        rowInline1.add(inlineKeyboardButton1);

        //update phoneNumber button
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("Update phoneNumber");
        inlineKeyboardButton2.setCallbackData("update phoneNumber");
        rowInline1.add(inlineKeyboardButton2);

        //update debt button
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("Update debt");
        inlineKeyboardButton3.setCallbackData("update debt");
        rowInline1.add(inlineKeyboardButton3);

        // Set the keyboard to the markup
        rowsInline.add(rowInline1);

        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        //Debts button
        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("Qarzlar ruyhati ");
        inlineKeyboardButton4.setCallbackData("debt list");
        rowInline2.add(inlineKeyboardButton4);

        // Delete button
        InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton();
        inlineKeyboardButton5.setText("Delete");
        inlineKeyboardButton5.setCallbackData("delete");
        rowInline2.add(inlineKeyboardButton5);


        // Set the keyboard to the markup
        rowsInline.add(rowInline2);

        // Add it to the message
        markupInline.setKeyboard(rowsInline);

        return markupInline;
    }
}
