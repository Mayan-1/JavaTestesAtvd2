package com.snack.repositories;

import com.snack.App;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


public class AppTest {

    @Test
    public void testShowMenu() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        App.showMenu();

        System.setOut(originalOut);

        String expectedOutput = "\n1 - New product\n" +
                "2 - Update product\n" +
                "3 - List products\n" +
                "4 - Sell\n" +
                "5 - Remove product\n" +
                "6 - Exit\n";

        assertEquals(expectedOutput, outContent.toString());
    }
}