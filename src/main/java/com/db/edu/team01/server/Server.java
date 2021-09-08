package com.db.edu.team01.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (final ServerSocket listener = new ServerSocket(10_000)) {
            while (true) {
                Socket connection = listener.accept();
                System.out.println("Got connection.");
                new Thread(new ServerService(connection)).start();
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
