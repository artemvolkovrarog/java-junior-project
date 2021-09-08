package com.db.edu.team01.decorator;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DecoratorTest {

    @Test
    public void shouldDecorateMessage(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String currentDate = dateFormat.format(date);

        String message = "test message";
        String username = "username";
        String decoratedMessage = Decorator.getFormattedStr(message, username);

        assertTrue(decoratedMessage.contains(message), "Should contain message");
        assertTrue(decoratedMessage.contains(username), "Should contain user name");
        assertTrue(decoratedMessage.contains(currentDate), "Should contain current date");
    }
}
