package sk.pocsik;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import sk.pocsik.things.Things;
import sk.pocsik.utils.ConsoleColors;
import sk.pocsik.utils.MyConsole;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;


class GameTest {
    private Game game;


    @BeforeEach
    public void beforeEach() {
        game = new Game();
    }

    @Test
    @DisplayName("Scores after init test")
    public void scoresInitTest() {
        int actualPlayerScore = this.game.getPlayerScore();
        assertEquals(0, actualPlayerScore, "player score should be zero on init");

        int actualComputerScore = game.getComputerScore();
        assertEquals(0, actualComputerScore, "player score should be zero on init");
    }

    @Test
    @DisplayName("Things init test")
    public void thingsTest() {
        Map<Integer, Things> actualThings = game.getThings();
        assertEquals(3, actualThings.size(), "Things size should be 3");

        String rock = actualThings.get(1).toString();
        assertEquals("Rock", rock, "Rock should had key 1");

        String paper = actualThings.get(2).toString();
        assertEquals("Paper", paper, "Paper should have key 2");

        String scissors = actualThings.get(3).toString();
        assertEquals("Scissors", scissors, "Scissors should have key 3");
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, -542, Integer.MAX_VALUE})
    @DisplayName("Start game methods test with integers - to quit")
    public void startGameWithIntegersTest(int number)  {
        String input = Integer.toString(number);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        setTestInput(input + "\n");

        game.startGame();

        String excepted = "You want to play a game? " + ConsoleColors.GREEN + "(y/n): " + ConsoleColors.RESET + "\r\n";
        excepted = excepted.concat("Good bye!\r\n");

        assertEquals(excepted, outContent.toString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"ahoj", "n", "no", "query", "1235sd"})
    @DisplayName("Start game methods test with strings - to quit")
    public void startGameWithStringsTest(String input)  {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        setTestInput(input + "\n");

        game.startGame();

        String excepted = "You want to play a game? " + ConsoleColors.GREEN + "(y/n): " + ConsoleColors.RESET + "\r\n";
        excepted = excepted.concat("Good bye!\r\n");

        assertEquals(excepted, outContent.toString());
    }


    private void setTestInput(String input) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);
        MyConsole.setScanner(new Scanner(testIn));
    }
}