import java.io.Serializable;

public class Dado implements Serializable {
    private static final long serialVersionUID = 1L; // Para garantir a compatibilidade na serialização
    private int sideUP;

    // Construtor inicializa o dado com o lado 1
    public Dado() {
        this.sideUP = 1;
    }

    // Método para rolar o dado e definir o lado atual
    public void roll() {
        this.sideUP = (int)(Math.random() * 6) + 1;
    }

    // Retorna o lado que está virado para cima
    public int getSideUp() {
        return this.sideUP;
    }

    @Override
    public String toString() {
        return Integer.toString(this.sideUP);
    }
}
