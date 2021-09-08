package com.db.edu.team01.save;

import com.db.edu.team01.decorator.Decorator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;

class SaverTest {
    private final String fileName = "testmessagebase.txt";
    File source = new File(fileName);

    void resetFile(){
        try {
            PrintWriter writer = new PrintWriter(fileName);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveMessageToFileCorrectly() throws IOException {
        resetFile();
        source.createNewFile();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new BufferedInputStream(
                                new FileInputStream(source))));
        String expected = Decorator.getFormattedStr("Hello", "Name");
        Saver saver = new Saver(fileName);
        saver.save("Hello", "Name");
        Assertions.assertEquals(expected, in.readLine());
        in.close();
        source.delete();
    }
    @Test
    public void getHistoryCorrectly() throws IOException {
        resetFile();
        source.createNewFile();
        Saver saver = new Saver(fileName);
        String firstName = "firstName";
        String secondName = "secondName";
        String firstHello = "firstHello";
        String secondHello = "secondHello";
        List<String> expected = Arrays.asList(
                Decorator.getFormattedStr(firstHello, firstName), Decorator.getFormattedStr(secondHello, secondName));
        source.delete();
        saver.save(firstHello, firstName);
        saver.save(secondHello, secondName);
        Assertions.assertEquals(expected, saver.getHistory());
    }

}