import java.io.Serializable;

public class Dado implements Serializable {
    private static final long serialVersionUID = 1L; // Para garantir a compatibilidade na serialização
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
