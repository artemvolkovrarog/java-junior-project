package com.db.edu.team01.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientServiceServerListener implements Runnable {
    private final String responseSeparator = "&sep&";
    private final Object writeMonitor;
    private DataInputStream input;
    private Socket connection;
    private Scanner inScan;

    public ClientServiceServerListener(Object writeMonitor, DataInputStream input, Socket connection) {
        this.writeMonitor = writeMonitor;
        this.input = input;
        this.connection = connection;
        try {
            this.inScan = new Scanner(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                listenToServer();
            } catch (IOException e) {
                System.out.println("Can't listen to server");
            }
        }
    }

    private void listenToServer() throws IOException {
        if (inScan.hasNext()) {
            String answer = input.readUTF();
            writeServerAnswer(answer);
        }
    }

    private void writeServerAnswer(String answer) {
        synchronized (writeMonitor) {
            String[] lines = answer.split(responseSeparator);

            for (String line : lines) {
                System.out.println(line);
            }
        }
    }
}
