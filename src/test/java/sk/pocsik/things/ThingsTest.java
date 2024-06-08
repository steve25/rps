package sk.pocsik.things;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ThingsTest {

    private static Things rock = new Rock();
    private static Things paper = new Paper();
    private static Things scissors = new Scissors();

    @BeforeAll
    public static void beforeAll() {
        rock = new Rock();
        paper = new Paper();
        scissors = new Scissors();
    }

    @Test
    @DisplayName("Test Rock beating or not")
    public void rockTest() {
        assertTrue(rock.beat(scissors));
        assertFalse(rock.beat(paper));
    }

    @Test
    @DisplayName("Test Paper beating or not")
    public void paperTest() {
        assertTrue(paper.beat(rock));
        assertFalse(paper.beat(scissors));
    }

    @Test
    @DisplayName("Test Scissors beating or not")
    public void scissorsTest() {
        assertTrue(scissors.beat(paper));
        assertFalse(scissors.beat(rock));
    }
}
