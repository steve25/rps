package sk.pocsik.things;

public class Paper implements Things {
    @Override
    public String toString() {
        return "Paper";
    }

    public boolean beat(Things opponentAnswer) {
        return opponentAnswer.toString().equals("Rock");
    }
}
