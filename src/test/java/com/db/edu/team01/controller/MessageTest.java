package com.db.edu.team01.controller;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MessageTest {
    private static final int LIMIT = 150;
    private static String VALID_PAYLOAD = RandomStringUtils.random(LIMIT);
    private static String LONG_PAYLOAD = RandomStringUtils.random(LIMIT + 1);

    @Test
    public void shouldReturnTrueIfMessageValid() {
        Message message = new Message("/chid", VALID_PAYLOAD);
        assertTrue(message.payloadIsValid(), "Payload length checker should return true");
    }

    @Test
    public void shouldReturnFalseIfMessageInvalid() {

        Message message = new Message("/chid", LONG_PAYLOAD);
        assertFalse(message.payloadIsValid(), "Payload length checker should return true");
    }

}
