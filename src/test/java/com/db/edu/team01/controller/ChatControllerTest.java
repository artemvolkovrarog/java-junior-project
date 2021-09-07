package com.db.edu.team01.controller;

import org.junit.jupiter.api.Test;

import java.io.DataOutputStream;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class ChatControllerTest {
    @Test
    public void shouldHandleCorrectInput() throws IOException {
        String testInput = "/chid testUser1";
        DataOutputStream outputMock = mock(DataOutputStream.class);
        ChatController chatController = new ChatController(outputMock);

        chatController.handleInput(testInput);
        verify(outputMock, times(1)).writeUTF("Added user");
        verify(outputMock, times(1)).flush();
    }
}
