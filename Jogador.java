import java.io.Serializable;

public class Jogador implements Serializable {
    private static final long serialVersionUID = 1L; // Identificador único para garantir a compatibilidade na serialização

    private String id; // Identificador único do jogador
    private boolean humano; // Indica se o jogador é humano (true) ou uma máquina (false)
    private double saldo; // Saldo atual do jogador
    private double aposta; // Valor da aposta atual do jogador
    private int numeroDeJogadas; // Contador do número de jogadas realizadas pelo jogador

    // Construtor da classe Jogador
    public Jogador(String id, boolean humano) {
        this.id = id; // Inicializa o identificador do jogador
        this.humano = humano; // Inicializa o tipo de jogador (humano ou máquina)
        this.saldo = 0; // Inicializa o saldo do jogador com 0
        this.aposta = 0; // Inicializa o valor da aposta com 0
        this.numeroDeJogadas = 0; // Inicializa o número de jogadas com 0
    }

    // Retorna o identificador do jogador
    public String getId() {
        return id;
    }

    // Retorna se o jogador é humano
    public boolean isHumano() {
        return humano;
    }

    // Retorna o saldo atual do jogador
    public double getSaldo() {
        return saldo;
    }

    // Define o saldo do jogador
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // Retorna o valor da aposta do jogador
    public double getAposta() {
        return aposta;
    }

    // Define o valor da aposta do jogador
    public void setAposta(double aposta) {
        this.aposta = aposta;
    }

    // Ajusta o saldo do jogador, adicionando o valor fornecido
    public void ajustarSaldo(double valor) {
        this.saldo += valor;
    }

    // Incrementa o número de jogadas realizadas pelo jogador
    public void incrementarNumeroDeJogadas() {
        this.numeroDeJogadas++;
    }

    // Retorna o número de jogadas realizadas pelo jogador
    public int getNumeroDeJogadas() {
        return numeroDeJogadas;
    }

    // Retorna uma representação textual do jogador, incluindo o ID, saldo e número de jogadas
    @Override
    public String toString() {
        return String.format("Jogador %s - Saldo: %.2f - Número de Jogadas: %d", id, saldo, numeroDeJogadas);
    }
}
