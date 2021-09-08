package com.db.edu.team01.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Callable;

public class ClientServiceServerListener implements Callable<Integer> {
    private static final String responseSeparator = "&sep&";
    private final DataInputStream input;
    private final Socket connection;

    public ClientServiceServerListener(DataInputStream input, Socket connection) {
        this.input = input;
        this.connection = connection;
    }

    @Override
    public Integer call() {
        while (true) {
            try {
                listenToServer();
            } catch (IOException e) {
                if (connection.isClosed()) {
                    System.out.println("Return from server-future");
                    Thread.currentThread().interrupt();
                    return 1;
                } else {
                    System.out.println("Exception: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    private void listenToServer() throws IOException {
        String answer = input.readUTF();
        writeServerAnswer(answer);
    }

    private void writeServerAnswer(String answer) {
        String[] lines = answer.split(responseSeparator);

        for (String line : lines) {
            System.out.println(line);
        }
    }
}
