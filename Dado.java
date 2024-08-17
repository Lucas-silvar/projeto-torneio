import java.util.Random;

public class Dado {
    private int valor;
    private static final Random random = new Random();

    public Dado() {
        rolar();
    }

    public void rolar() {
        valor = random.nextInt(6) + 1; // Gera um valor entre 1 e 6
    }

    public int getValor() {
        return valor;
    }
}
