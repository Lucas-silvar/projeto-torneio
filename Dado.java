import java.util.Random;

public class Dado {
    private int valor;  // Representa o valor da face atual do dado

    // Construtor que inicializa o dado
    public Dado() {
        this.lancar();  // Chama o método para lançar o dado ao criar
    }

    // Método para lançar o dado e definir o valor da face
    public void lancar() {
        Random random = new Random();
        this.valor = random.nextInt(6) + 1;  // Gera um número entre 1 e 6
    }

    // Método para obter o valor da face atual
    public int getValor() {
        return valor;
    }

    // Representação textual do valor do dado
    @Override
    public String toString() {
        return "Dado: " + valor;
    }
}
