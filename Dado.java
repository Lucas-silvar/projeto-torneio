import java.io.Serializable;

public class Dado implements Serializable {
    private static final long serialVersionUID = 1L; // Identificador único para garantir a compatibilidade na serialização
    private int sideUP; // Valor atual da face voltada para cima do dado

    // Construtor da classe Dado
    public Dado() {
        this.sideUP = 1; // Inicializa o dado com a face 1 voltada para cima
    }

    // Simula o lançamento do dado, atribuindo um valor aleatório entre 1 e 6 à face voltada para cima
    public void roll() {
        this.sideUP = (int)(Math.random() * 6) + 1; // Gera um número aleatório entre 1 e 6
    }

    // Retorna o valor da face voltada para cima do dado
    public int getSideUp() {
        return this.sideUP;
    }

    // Retorna uma representação textual do dado, que é o valor da face voltada para cima
    @Override
    public String toString() {
        return Integer.toString(this.sideUP); // Converte o valor da face voltada para cima em uma string
    }
}
