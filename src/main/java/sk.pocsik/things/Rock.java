package sk.pocsik.things;

public class Rock implements Things {
    @Override
    public String toString() {
        return "Rock";
    }

    public boolean beat(Things opponentAnswer) {
        return opponentAnswer.toString().equals("Scissors");
    }
}
