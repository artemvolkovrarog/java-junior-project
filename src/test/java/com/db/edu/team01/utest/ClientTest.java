package com.db.edu.team01.utest;

import com.db.edu.team01.client.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.DataOutputStream;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class ClientTest implements SysoutCaptureAndAssertionAbility{
    @BeforeEach
    public void setUpSystemOut() {
        resetOut();
        captureSysout();
    }

    @AfterEach
    public void tearDown() {
        resetOut();
    }


//    @Test
//    public void shouldSendMessageToServer() throws IOException {
//        DataOutputStream outputMock = mock(DataOutputStream.class);
//        String message = "/snd hi";
//
//        Client.sendMessage(outputMock, message);
//
//        verify(outputMock, times(1)).flush();
//    }
}
