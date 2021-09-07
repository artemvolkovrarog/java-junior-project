package com.db.edu.team01.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ClientServiceInputListener implements Runnable {
    private final Object writeMonitor;
    private final Scanner in;
    private final DataOutputStream output;

    public ClientServiceInputListener(Object writeMonitor, Scanner in, DataOutputStream output) {
        this.writeMonitor = writeMonitor;
        this.in = in;
        this.output = output;
    }

    @Override
    public void run() {
        while (true) {
            try {
                listenToClient();
            } catch (IOException e) {
                System.out.println("Can't listen to client");
            }
        }
    }

    private void listenToClient() throws IOException {
        if (in.hasNext()) {
            String userInput = in.nextLine();

            synchronized (writeMonitor) {
                output.writeUTF(userInput);
                output.flush();
            }
        }
    }
}
