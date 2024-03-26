package things;

public class Scissors implements Things {
    @Override
    public String toString() {
        return "Scissors";
    }

    public boolean beat(Things things) {
        return things.toString().equals("Paper");
    }
}
