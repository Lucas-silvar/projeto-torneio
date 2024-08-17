public class Jogador {
    private String id;
    private String tipo; // humano ou máquina
    private int saldo;
    private int aposta;
    private Dado[] dados;

    public Jogador(String id, String tipo) {
        this.id = id;
        this.tipo = tipo;
        this.saldo = 100; // Valor inicial padrão
        this.dados = new Dado[5]; // Para o Jogo de Bozó
        for (int i = 0; i < dados.length; i++) {
            dados[i] = new Dado();
        }
    }

    public String getId() {
        return id;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setAposta(int aposta) {
        this.aposta = aposta;
    }

    public void ajustarSaldo(int valor) {
        saldo += valor;
    }

    public void rolarDados() {
        for (Dado dado : dados) {
            dado.rolar();
        }
    }

    public Dado[] getDados() {
        return dados;
    }

    public int getPontuacao() {
        // Aqui você pode implementar a lógica para calcular a pontuação se necessário
        return saldo; // Exemplo básico
    }

    @Override
    public String toString() {
        return id + " - Saldo: " + saldo;
    }
}
