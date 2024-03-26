import java.util.Scanner;

public class MyConsole {
    public static Scanner sc = new Scanner(System.in);

    public static boolean getPlaying() {
        System.out.print("You want to play a game? " + ConsoleColors.GREEN + "(y/n): " + ConsoleColors.RESET);
        String answer = MyConsole.sc.nextLine();
        System.out.println();
        return answer.equals("y");
    }

    public static int choose() {
        System.out.println(ConsoleColors.GREEN + "1." + ConsoleColors.RESET + " Rock ");
        System.out.println(ConsoleColors.GREEN + "2." + ConsoleColors.RESET + " Paper");
        System.out.println(ConsoleColors.GREEN + "3." + ConsoleColors.RESET + " Scissors");
        System.out.print("Choose your option: ");

        int answer = 0;
        do {
            while (!sc.hasNextInt()) {
                System.err.print("Please enter a number between 1 and 3: ");
                sc.next();
            }

            answer = sc.nextInt();
            sc.nextLine();
        } while (answer < 1 || answer > 3);

        System.out.println();

        return answer;
    }

    public static void printResult(char winner, int playerScore, int computerScore) {
        System.out.println();
        switch (winner) {
            case 'p':
                System.out.println(ConsoleColors.GREEN + "You win!" + ConsoleColors.RESET);
                break;
            case 'c':
                System.out.println(ConsoleColors.RED + "You lose!" + ConsoleColors.RESET);
                break;
            case 'd':
                System.out.println(ConsoleColors.YELLOW + "It's a tie!" + ConsoleColors.RESET);
                break;
            default:
                System.out.println("Something went wrong!");
                break;
        }

        System.out.println();
        System.out.println("You " + ConsoleColors.YELLOW_BOLD + playerScore + " : " + computerScore + ConsoleColors.RESET + " Computer");
        System.out.println();
    }

    public static void printFinalResult(char winner) {
        System.out.println(winner == 'p'
                ? "You " + ConsoleColors.GREEN + "WIN" + ConsoleColors.RESET + " this game!"
                : "You " + ConsoleColors.RED + "LOSE" + ConsoleColors.RESET + " this game!"
        );
        System.out.println();
    }
}
