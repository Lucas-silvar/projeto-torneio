public class Dado {
    private int valor;
    private static final int MAX = 6; // Valor máximo de um dado

    public Dado() {
        lançar(); // Inicializa o dado com um valor aleatório
    }

    public void lançar() {
        valor = (int) (Math.random() * MAX) + 1;
    }

    public int getValor() {
        return valor;
    }
}
