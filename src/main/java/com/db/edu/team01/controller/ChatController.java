package com.db.edu.team01.controller;

import com.db.edu.team01.formatter.Formatter;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class ChatController {
    private final DataOutputStream output;
    private String userName;
    private FileSaver;

    public ChatController(DataOutputStream output) {
        this.output = output;
        this.userName = null;
    }

    public void parser(String msg) throws IOException {
        String[] input = msg.split(" ");

        // Check that command has one argument
        if (input.length != 2 ) {
            throw new IllegalArgumentException("Command should have one argument");
        }

        String command = input[0];
        String payload = input[1];

        switch (command) {
            case Command.CMD_SEND.body:
                System.out.println("Sending your message...");
                sendMessage(payload);
                return;
            case Command.CMD_HISTORY.body:
                System.out.println("Getting chat history...");
                getHistory();
                return;
            case Command.CMD_IDENTIFY.body:
                System.out.println("Identifying...");
                setUserName(payload);
                return;
        }
    }

    private void sendMessage(String msg) {
        if (userName == null ) {
            output.writeUTF("Firstly, provide your name");
            return;
        }
        writeMessage(msg);
        FileSaver.save(msg, userName);
    }

    private void getHistory() throws IOException {
        List<String> lines = FileSaver.getHistory();
        for (String line : lines) {
            output.writeUTF(line);
        }
    }

    private void writeMessage(String msg) {
        String formattedStr = Formatter.getFormattedStr(msg, userName);
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
