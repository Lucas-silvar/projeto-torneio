import java.util.Random;

public class Dado {
    private int valor;

    public Dado() {
        this.lancar();
    }

    public void lancar() {
        Random random = new Random();
        this.valor = random.nextInt(6) + 1;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Dado: " + valor;
    }
}
