package com.db.edu.team01.decorator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Decorator {
    private static final int messageLength = 90;

    public static String getFormattedStr(String msg, String userName) {
        StringBuilder result = new StringBuilder();

        Date date = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        String time = format1.format(date); // 28.11.1999 09:03

        int spacesAmount = messageLength - time.length() - userName.length() - msg.length();
        result.append(userName).append(": ");
        int start = 0;
        int translateAmount = messageLength - time.length() - userName.length() - 2;

        while (spacesAmount < 0) {
            spacesAmount += translateAmount;
            result.append(msg, start, start + translateAmount)
                    .append(System.lineSeparator())
                    .append(getSpaces(2 + userName.length()));

            msg = msg.substring(start + translateAmount);
            start += translateAmount;
        }

        return result + msg + " ".repeat(spacesAmount) + time;
    }

    private static String getSpaces(int n) {

        return " ".repeat(Math.max(0, n));
    }
}
