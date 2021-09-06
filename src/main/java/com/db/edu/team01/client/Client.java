package com.db.edu.team01.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (
                final Socket connection = new Socket("localhost", 10_000);
                final DataInputStream input = new DataInputStream(new BufferedInputStream(connection.getInputStream()));
                final DataOutputStream output = new DataOutputStream(new BufferedOutputStream(connection.getOutputStream()));
                final Scanner in = new Scanner(System.in)
        ) {
            listenToClient(in, output);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public static void sendMessage(DataOutputStream out, String userInput) throws IOException {
        out.writeUTF(userInput);
        out.flush();
    }

    private static void listenToClient(Scanner in, DataOutputStream out) throws IOException {
        while (true) {
            String userInput = in.nextLine();
            sendMessage(out, userInput);
        }
    }
}