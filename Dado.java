public class Dado {
    private int sideUP;

    public Dado() {
        this.sideUP = 1;
    }

    public void roll() {
        this.sideUP = (int)(Math.random() * 6) + 1;
    }

    public int getSideUp() {
        return this.sideUP;
    }

    @Override
    public String toString() {
        return Integer.toString(this.sideUP);
    }
}
/