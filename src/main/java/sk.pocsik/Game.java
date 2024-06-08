package sk.pocsik;

import sk.pocsik.things.Paper;
import sk.pocsik.things.Rock;
import sk.pocsik.things.Scissors;
import sk.pocsik.things.Things;
import sk.pocsik.utils.ConsoleColors;
import sk.pocsik.utils.MyConsole;
import sk.pocsik.utils.SoundPlayer;

import java.util.HashMap;
import java.util.Map;


public class Game {
    private int playerScore;
    private int computerScore;
    private final Map<Integer, Things> things;

    public Game() {
        this.things = new HashMap<>() {{
            put(1, new Rock());
            put(2, new Paper());
            put(3, new Scissors());
        }};
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public Map<Integer, Things> getThings() {
        return things;
    }

    public void startGame() {
        while (true) {
            boolean isPlaying = MyConsole.getPlaying();

            if (!isPlaying) {
                System.out.println("Good bye!");
                break;
            }

            round();
        }
    }

    private void round() {

        while (Math.abs(playerScore - computerScore) != 2) {
            Things myAnswer = things.get(MyConsole.choose());
            System.out.println("Your choice is " + ConsoleColors.YELLOW + myAnswer + ConsoleColors.RESET);

            Things opponentAnswer = computerChoice();
            System.out.println("Computers choice is " + ConsoleColors.YELLOW + opponentAnswer + ConsoleColors.RESET);

            char winner = whoWin(myAnswer, opponentAnswer);

            MyConsole.printResult(winner, playerScore, computerScore);

            makeStuffs(winner);

        }
        char finalWinner = playerScore > computerScore? 'p' : 'c';
        MyConsole.printFinalResult(finalWinner, playerScore, computerScore);

    }

    private void makeStuffs(char winner) {
        SoundPlayer soundPlayer = new SoundPlayer();

        switch (winner) {
            case 'p':
                soundPlayer.playWin();
                break;
            case 'c':
                soundPlayer.playLoose();
                break;
            default:
                soundPlayer.playDraw();
                break;
        }
    }

    private char whoWin(Things myAnswer, Things opponentAnswer) {
        if (myAnswer.beat(opponentAnswer)) {
            playerScore++;
            return 'p';
        }

        if (opponentAnswer.beat(myAnswer)) {
            computerScore++;
            return 'c';
        }

        return 'd';
    }

    private Things computerChoice() {
        return things.get(1 + (int) (Math.random() * 3));
    }
}
