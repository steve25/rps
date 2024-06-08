package sk.pocsik.things;

public class Scissors implements Things {
    @Override
    public String toString() {
        return "Scissors";
    }

    public boolean beat(Things opponentAnswer) {
        return opponentAnswer.toString().equals("Paper");
    }
}
