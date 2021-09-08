package com.db.edu.team01.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Client {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Integer> serverListener;

        try (
                final Socket connection = new Socket("localhost", 10_000);
                final DataInputStream input = new DataInputStream(new BufferedInputStream(connection.getInputStream()));
                final DataOutputStream output = new DataOutputStream(new BufferedOutputStream(connection.getOutputStream()));
                final Scanner in = new Scanner(System.in)
        ) {

            serverListener = es.submit(new ClientServiceServerListener(input, connection));
            es.execute(new ClientServiceInputListener(output, in, connection));

            try {
                serverListener.get();
                connection.close();
                System.out.println("kernel is closed--");
                es.shutdownNow();
                System.exit(0);
            } catch (ExecutionException e) {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}