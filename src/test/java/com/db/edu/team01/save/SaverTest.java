package com.db.edu.team01.save;

import com.db.edu.team01.decorator.Decorator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;

class SaverTest {
    private final String fileName = "src/test/java/com/db/edu/team01/save/testmessagebase.txt";
    File source = new File(fileName);
    @Test
    public void saveMessageToFileCorrectly() throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new BufferedInputStream(
                                new FileInputStream(source))));
        String expected = Decorator.getFormattedStr("Hello", "Name");
        Saver.save("Hello", "Name");
        Assertions.assertEquals(expected, in.readLine());
    }
    @Test
    public void getHistoryCorrectly() throws IOException {
        String firstName = "firstName";
        String secondName = "secondName";
        String firstHello = "firstHello";
        String secondHello = "secondHello";
        List<String> expected = Arrays.asList(
                Decorator.getFormattedStr(firstHello, firstName), Decorator.getFormattedStr(secondHello, secondName));
        Saver.save("firstHello", "firstName");
        Saver.save("secondHello", "secondName");
        Assertions.assertEquals(expected, Saver.getHistory());
    }

}