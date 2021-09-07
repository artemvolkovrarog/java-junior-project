package com.db.edu.team01.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String responseSeparator = "&sep&";

    public static void main(String[] args) {
        try (
                final Socket connection = new Socket("localhost", 10_000);
                final DataInputStream input = new DataInputStream(new BufferedInputStream(connection.getInputStream()));
                final DataOutputStream output = new DataOutputStream(new BufferedOutputStream(connection.getOutputStream()));
                final Scanner in = new Scanner(System.in)
        ) {
            while (true) {
                listenToClient(in, output);
                listenToServer(input);
            }

        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public static void sendMessage(DataOutputStream out, String userInput) throws IOException {
        out.writeUTF(userInput);
        out.flush();
    }

    private static void listenToClient(Scanner in, DataOutputStream out) throws IOException {
        String userInput = in.nextLine();
        sendMessage(out, userInput);
    }

    private static void listenToServer(DataInputStream input) throws IOException {
        String[] answer = input.readUTF().split(responseSeparator);

        for (String line : answer) {
            System.out.println(line);
        }
    }
}