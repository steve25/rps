package things;

public class Rock implements Things {
    @Override
    public String toString() {
        return "Rock";
    }

    public boolean beat(Things things) {
        return things.toString().equals("Scissors");
    }
}
