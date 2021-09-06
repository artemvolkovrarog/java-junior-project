package com.db.edu.team01.controller;

import com.db.edu.team01.decorator.Decorator;
import com.db.edu.team01.save.Saver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import static com.db.edu.team01.controller.Command.*;

public class ChatController {
    private final DataOutputStream output;
    private String userName;
    private Saver fileSaver;

    public ChatController(DataOutputStream output) {
        this.output = output;
        this.userName = null;
    }

    public void parseMessage(String msg) throws IOException {
        String[] input = msg.split(" ");

        // Check that command has one argument
        if (input.length != 2 ) {
            throw new IllegalArgumentException("Command should have one argument");
        }

        String command = input[0];
        String payload = input[1];

        switch (command) {
            case CMD_SEND.body:
                System.out.println("Sending your message...");
                sendMessage(payload);
                break;
            case CMD_HISTORY.body:
                System.out.println("Getting chat history...");
                getHistory();
                break;
            case CMD_IDENTIFY:
                System.out.println("Identifying...");
                setUserName(payload);
                break;
            default:
                break;
        }
    }

    private void sendMessage(String msg) {
        if (userName == null ) {
            try {
                output.writeUTF("Firstly, provide your name");
            } catch (IOException e) {
                System.out.println("Error. Please try again");
            }
            return;
        }
        writeMessage(msg);
        fileSaver.save(msg, userName);
    }

    private void getHistory() throws IOException {
        List<String> lines = fileSaver.getHistory();
        for (String line : lines) {
            output.writeUTF(line);
        }
    }

    private void writeMessage(String msg) {
        String formattedStr = Decorator.getFormattedStr(msg, userName);
        try {
            output.writeUTF(formattedStr);
        } catch (IOException e) {
            System.out.println("Error in writing message. Repeat pls");
        }
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }


}
