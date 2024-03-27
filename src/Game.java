import things.Paper;
import things.Rock;
import things.Scissors;
import things.Things;

import java.util.HashMap;
import java.util.Map;


public class Game {
    private Map<Integer, Things> things = new HashMap<>() {{
        put(1, new Rock());
        put(2, new Paper());
        put(3, new Scissors());
    }};

    private int playerScore;
    private int computerScore;


    public Game() {
        this.playerScore = 0;
        this.computerScore = 0;
        gameLoop();
    }

    public void gameLoop() {
        while (true) {
            boolean isPlaying = MyConsole.getPlaying();

            if (!isPlaying) {
                System.out.println("Good bye!");
                break;
            }

            round();
        }
    }

    public void round() {

        while (playerScore < 5 && computerScore < 5) {
            Things myAnswer = things.get(MyConsole.choose());
            System.out.println("Your choice is " + ConsoleColors.YELLOW + myAnswer + ConsoleColors.RESET);

            Things oponentAnswer = computerChoice();
            System.out.println("Computers choice is " + ConsoleColors.YELLOW + oponentAnswer + ConsoleColors.RESET);

            char winner = whoWin(myAnswer, oponentAnswer);

            MyConsole.printResult(winner, playerScore, computerScore);

            makeStuffs(winner);

        }
        MyConsole.printFinalResult(playerScore == 5 ? 'p' : 'c', playerScore, computerScore);

    }

    private void makeStuffs(char winner) {
        SoundPlayer player = new SoundPlayer();

        switch (winner) {
            case 'p':
                player.playWin();
                break;
            case 'c':
                player.playLoose();
                break;
            default:
                player.playDraw();
                break;
        }
    }

    private char whoWin(Things myAnswer, Things oponentAnswer) {
        if (myAnswer.beat(oponentAnswer)) {
            playerScore++;
            return 'p';
        } else if (oponentAnswer.beat(myAnswer)) {
            computerScore++;
            return 'c';
        }

        return 'd';
    }

    private Things computerChoice() {
        return things.get(1 + (int) (Math.random() * 3));
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getComputerScore() {
        return computerScore;
    }
}
