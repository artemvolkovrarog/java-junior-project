package com.db.edu.team01.save;

import com.db.edu.team01.decorator.Decorator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;

class SaverTest {
    private final String fileName = "testmessagebase.txt";
    File source = new File(fileName);
    @Test
    @Disabled
    public void saveMessageToFileCorrectly() throws IOException {
        Saver saver = new Saver("testmessagebase.txt");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new BufferedInputStream(
                                new FileInputStream(source))));
        String expected = Decorator.getFormattedStr("Hello", "Name");
        saver.save("Hello", "Name");
        Assertions.assertEquals(expected, in.readLine());
    }
    @Test
    @Disabled
    public void getHistoryCorrectly() throws IOException {
        Saver saver = new Saver("testmessagebase.txt");
        String firstName = "firstName";
        String secondName = "secondName";
        String firstHello = "firstHello";
        String secondHello = "secondHello";
        List<String> expected = Arrays.asList(
                Decorator.getFormattedStr(firstHello, firstName), Decorator.getFormattedStr(secondHello, secondName));
        saver.save("firstHello", "firstName");
        saver.save("secondHello", "secondName");
        Assertions.assertEquals(expected, saver.getHistory());
    }

}