package sk.pocsik.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class MyConsoleTest {

    @ParameterizedTest
    @DisplayName("Test getPlaying method with string inputs to not continue")
    @ValueSource(strings = {"ahoj", "n", "no", "query", "1235sd"})
    public void getPlayingStringNotContinueTest(String input) {

        setTestInput(input + "\n");
        boolean result = MyConsole.getPlaying();
        assertFalse(result);
    }

    @ParameterizedTest
    @DisplayName("Test getPlaying method with int inputs to not continue")
    @ValueSource(ints = {-1, 2, 55, -189, 564654})
    public void getPlayingIntNotContinueTest(int input) {

        setTestInput(input + "\n");
        boolean result = MyConsole.getPlaying();
        assertFalse(result);
    }

    @Test
    @DisplayName("Test getPlaying method to continue")
    public void getPlayingContinueTest() {
        setTestInput("y\n");
        boolean result = MyConsole.getPlaying();
        assertTrue(result);
    }

    @ParameterizedTest
    @DisplayName("Test choose method with String inputs")
    @CsvSource({
            "'abc,1', 1",
            "'xyz,2', 2",
            "'nope,3', 3",
            "'wrong input,another wrong input,1', 1"
    })
    public void chooseStringTest(String inputs, int excepted) {
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        String input = inputs.replace(",", "\n") + "\n";

        setTestInput(input + "\n");

        int result = MyConsole.choose();

        assertEquals(excepted, result);

        assertTrue(errContent.toString().contains("Please enter a valid number"));
    }

    @ParameterizedTest
    @DisplayName("Test choose method with not between inputs")
    @CsvSource({
            "'55,1', 1",
            "'77,2', 2",
            "'-85,3', 3",
            "'-1,265,1', 1"
    })
    public void chooseMoreOrLessTest(String inputs, int excepted) {
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        String input = inputs.replace(",", "\n") + "\n";

        setTestInput(input + "\n");

        int result = MyConsole.choose();

        assertEquals(excepted, result);

        assertTrue(errContent.toString().contains("Number must be"));
    }

    @ParameterizedTest
    @DisplayName("Test print a result")
    @CsvSource({
            "p, You win this round!",
            "c, You lose this round!",
            "d, It's a tie!",
            "f, Something went wrong!",
            "q, Something went wrong!"
    })
    public void printResultTest(char winner, String expected) {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        MyConsole.printResult(winner, 0, 0);

        assertTrue(outContent.toString().contains(expected));

    }



    private void setTestInput(String input) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);
        MyConsole.setScanner(new Scanner(testIn));
    }
}
