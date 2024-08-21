import java.io.Serializable;

public class Jogador implements Serializable {
    private static final long serialVersionUID = 1L; // Para garantir a compatibilidade na serialização
    private String id;
    private boolean humano;
    private double saldo;
    private double aposta;
    private int numeroDeJogadas;

    public Jogador(String id, boolean humano) {
        this.id = id;
        this.humano = humano;
        this.saldo = 0;
        this.aposta = 0;
        this.numeroDeJogadas = 0;
    }

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

    public void ajustarSaldo(double valor) {
        this.saldo += valor;
    }

    // Sobrecarga do método ajustarSaldo
    public void ajustarSaldo(int valor) {
        this.saldo += valor;
    }

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
