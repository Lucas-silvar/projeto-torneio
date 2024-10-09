import java.io.Serializable;

public class Jogador implements Serializable {
    private static final long serialVersionUID = 1L; // Para garantir a compatibilidade na serialização
    private String id; // ID do jogador
    private boolean humano; // Indica se o jogador é humano
    private double saldo; // Saldo atual do jogador
    private double aposta; // Valor da aposta do jogador
    private int numeroDeJogadas; // Número de jogadas do jogador

    // Construtor inicializa o jogador com ID e tipo (humano ou máquina)
    public Jogador(String id, boolean humano) {
        this.id = id;
        this.humano = humano;
        this.saldo = 0;
        this.aposta = 0;
        this.numeroDeJogadas = 0;
    }

    // Métodos getters e setters
    public String getId() {
        return id;
    }

    public boolean isHumano() {
        return humano;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getAposta() {
        return aposta;
    }

    public void setAposta(double aposta) {
        this.aposta = aposta;
    }

    // Ajusta o saldo do jogador (adiciona ou subtrai o valor)
    public void ajustarSaldo(double valor) {
        this.saldo += valor;
    }

    // Incrementa o número de jogadas do jogador
    public void incrementarNumeroDeJogadas() {
        this.numeroDeJogadas++;
    }

    public int getNumeroDeJogadas() {
        return numeroDeJogadas;
    }

    @Override
    public String toString() {
        return String.format("Jogador %s - Saldo: %.2f - Número de Jogadas: %d", id, saldo, numeroDeJogadas);
    }
}
