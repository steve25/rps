package things;

public class Paper implements Things {
    @Override
    public String toString() {
        return "Paper";
    }

    public boolean beat(Things things) {
        return things.toString().equals("Rock");
    }
}
