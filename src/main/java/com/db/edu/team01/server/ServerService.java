package com.db.edu.team01.server;

import com.db.edu.team01.controller.ChatController;

import java.io.*;
import java.net.Socket;

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

            while (true) {
                final String read = input.readUTF();
                controller.handleInput(read);
            }

        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
