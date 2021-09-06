package com.db.edu.save;

import java.io.*;

public class Saver {
    static final String fileName = "messageBase";

    String clientName;
    String message;

    public Saver(String clientName, String message) {
        this.clientName = clientName;
        this.message = message;
    }

    public void save() throws SaverException {
        File source = new File(fileName);
        try (BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(
                        new BufferedOutputStream(
                                new FileOutputStream(source))))) {
            out.write(clientName + ':' + message);

        } catch (IOException e) {
            throw new SaverException("Could not save message to ", e);
        }    }
}
