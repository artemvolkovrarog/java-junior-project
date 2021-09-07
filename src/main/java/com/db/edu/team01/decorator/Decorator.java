package com.db.edu.team01.decorator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Decorator {
    private static final int messageLength = 60;

    public static String getFormattedStr(String msg, String userName) {
        String result = "";

        Date date = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        String time = format1.format(date); // 28.11.1999 09:03

        int spacesAmount = messageLength - time.length() - userName.length() - msg.length();
        result += userName + ": ";
        int start = 0;
        int translateAmount = messageLength - time.length() - userName.length() - 2;

        while (spacesAmount < 0) {
            spacesAmount += translateAmount;
            result += msg.substring(start, start + translateAmount)
                    + System.lineSeparator()
                    + getSpaces(2 + userName.length());
            msg = msg.substring(start + translateAmount);
            start += translateAmount;
        }
        String spaces = "";

        for (int i=0; i<spacesAmount; i++)
            spaces += " ";

        return result + msg + spaces + time;
    }

    private static String getSpaces(int n) {
        String result = "";

        for (int i=0; i<n; i++)
            result += " ";

        return result;
    }
}
