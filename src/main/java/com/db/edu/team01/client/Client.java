package com.db.edu.team01.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (
                final Socket connection = new Socket("localhost", 10_000);
                final DataInputStream input = new DataInputStream(new BufferedInputStream(connection.getInputStream()));
                final DataOutputStream output = new DataOutputStream(new BufferedOutputStream(connection.getOutputStream()))
        ) {
            Object writeMonitor = new Object();

            new Thread(new ClientServiceServerListener(writeMonitor, input, connection)).start();
            new Thread(new ClientServiceInputListener(writeMonitor, output)).start();
            Thread.sleep(100000000);

        } catch (IOException e) {
            e.printStackTrace(System.err);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}