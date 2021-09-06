package com.db.edu.team01.save;

import com.db.edu.team01.decorator.Decorator;

import java.io.*;

public class Saver {
    static final String fileName = "messageBase";

    public static void save(String message, String clientName) throws SaverException {
        File source = new File(fileName);
        try (BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(
                        new BufferedOutputStream(
                                new FileOutputStream(source))))) {
            out.write(Decorator.getFormattedStr(message, clientName));

        } catch (IOException e) {
            throw new SaverException("Could not save message to ", e);
        }    }
}
