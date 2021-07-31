package coder.uz.telegram_debt_bot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Long id;
    private String fullName;
    private Date date;
    private String phoneNumber;
    private Double debt;
}
