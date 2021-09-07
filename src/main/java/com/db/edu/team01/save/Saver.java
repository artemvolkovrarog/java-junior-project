package com.db.edu.team01.save;

import com.db.edu.team01.decorator.Decorator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Saver {
    private String fileName;

    public Saver(String fileName) {
        this.fileName = fileName;
    }

    public void save(String message, String clientName) throws SaverException {
        File source = new File(fileName);

        try (BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(
                        new BufferedOutputStream(
                                new FileOutputStream(source, true))))) {
            out.write(Decorator.getFormattedStr(message, clientName) + System.lineSeparator());

        } catch (IOException e) {
            throw new SaverException("Could not save message to ", e);
        }
    }

    public List<String> getHistory() {
        Path testFile = Paths.get(fileName);
        List<String> lines = null;

        try {
            lines = Files.readAllLines(testFile);
        }
        catch (IOException e) {
            System.out.println("Error");
        }

        return lines;
    }
}
