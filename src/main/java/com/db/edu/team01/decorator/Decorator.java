package com.db.edu.team01.decorator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Decorator {
    public static String getFormattedStr(String msg, String userName) {
        Date date = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        String time = format1.format(date); // 28.11.1999 09:03

        return userName + ": " + msg + "  " + time;
    }
}
