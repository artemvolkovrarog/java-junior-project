package com.db.edu.team01.controller;

import org.junit.jupiter.api.Test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ChatControllerTest {
    private DataOutputStream outputMock = mock(DataOutputStream.class);
    private Socket socket = mock(Socket.class);
    private ChatController chatController = new ChatController(outputMock, socket);

    @Test
    public void shouldHandleCorrectInput() throws IOException {
        String testInput = "/chid testUser1";
        chatController.handleInput(testInput);
        verify(outputMock, times(1)).writeUTF("Added user");
        verify(outputMock, times(1)).flush();
    }

    @Test
    public void shouldNotifyWhenInputCommandIsInvalid() throws IOException {
        String testInput = "/test testInput";
        chatController.handleInput(testInput);
        verify(outputMock, times(1)).writeUTF("Unknown command, try again");
    }

    @Test
    public void shouldThrowExceptionWhenUsernameIsNull() {
        //TODO
    }
}
