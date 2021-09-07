package com.db.edu.team01.server;

import com.db.edu.team01.controller.ChatController;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerService implements Runnable {
    Socket connection;

    public ServerService(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try (
                final DataInputStream input = new DataInputStream(new BufferedInputStream(connection.getInputStream()));
                final DataOutputStream output = new DataOutputStream(new BufferedOutputStream(connection.getOutputStream()))
        ) {
            ChatController controller = new ChatController(output);
            Scanner inScan = new Scanner(connection.getInputStream());

            while (true) {
                if (inScan.hasNext()) {
                    final String read = input.readUTF();
                    controller.parseMessage(read);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
