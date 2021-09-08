package com.db.edu.team01.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientServiceInputListener implements Runnable {
    private final DataOutputStream output;
    private final Scanner in;
    private final Socket connection;

    public ClientServiceInputListener(DataOutputStream output, Scanner in, Socket connection) {
        this.output = output;
        this.in = in;
        this.connection = connection;
    }

    @Override
    public void run() {
        while (true) {
            try {
                listenToClient();
            } catch (IOException e) {
                System.out.println("Can't listen to client " + e.getMessage());
            }
        }
    }

    private void listenToClient() throws IOException {
        try {
            if (in.hasNext()) {
                String userInput = in.nextLine();

                output.writeUTF(userInput);
                output.flush();

                if (userInput.equals("/exit")) {
                    connection.close();
                    Thread.currentThread().interrupt();
                }
            }
        } catch (IllegalStateException e) {
            System.out.println("Scanner is dead");
        }
    }
}
