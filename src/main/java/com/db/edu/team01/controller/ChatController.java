package com.db.edu.team01.controller;

import com.db.edu.team01.decorator.Decorator;
import com.db.edu.team01.save.Saver;
import com.db.edu.team01.save.SaverException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ChatController {
    private static final String CMD_SEND = "/snd";
    private static final String CMD_HISTORY = "/hist";
    private static final String CMD_IDENTIFY = "/chid";

    private final DataOutputStream output;
    private final String responseSeparator = "&sep&";
    private String userName;
    private Saver fileSaver;

    public ChatController(DataOutputStream output) {
        this.output = output;
        this.userName = null;

        fileSaver = new Saver("messageBase");
    }

    public void parseMessage(String msg) throws IOException {
        String[] input = msg.split(" ", 2);
        String payload;

        String command = input[0];
        if (input.length > 1) {
            payload = input[1];
        } else {
            payload = "";
        }

        switch (command) {
            case CMD_SEND:
                System.out.println("Sending your message...");
                sendMessage(payload);
                break;
            case CMD_HISTORY:
                System.out.println("Getting chat history...");
                getHistory();
                break;
            case CMD_IDENTIFY:
                System.out.println("Identifying...");
                setUserName(payload);
                break;
            default:
                output.writeUTF("Unknown command, try again");
                output.flush();
                break;
        }
    }

    private void sendMessage(String msg) throws SaverException {
        if (userName == null ) {
            try {
                output.writeUTF("Firstly, provide your name");
                output.flush();
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

        String result = lines.stream()
                    .collect(Collectors.joining(responseSeparator));

        output.writeUTF(result);
        output.flush();
    }

    private void writeMessage(String msg) {
        String formattedStr = Decorator.getFormattedStr(msg, userName);
        try {
            output.writeUTF(formattedStr);
            output.flush();
        } catch (IOException e) {
            System.out.println("Error in writing message. Repeat pls");
        }
    }

    private void setUserName(String userName) {
        this.userName = userName;
        try {
            output.writeUTF("Added user");
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
