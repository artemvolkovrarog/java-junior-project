package com.db.edu.team01.server;

import com.db.edu.team01.controller.ChatController;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (final ServerSocket listener = new ServerSocket(10_000)) {
//            listener.setSoTimeout(5000000);
            try (
                    final Socket connection = listener.accept();
                    final DataInputStream input = new DataInputStream(new BufferedInputStream(connection.getInputStream()));
                    final DataOutputStream output = new DataOutputStream(new BufferedOutputStream(connection.getOutputStream()))
            ) {
                System.out.println("Server is running.");
                ChatController controller = new ChatController(output);

                while (true) {
                    final String read = input.readUTF();
                    controller.parseMessage(read);
                }

            } catch (IOException e) {
                e.printStackTrace(System.err);
            }

        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
