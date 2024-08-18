public class Jogador {
    private String id;
    private String tipo;
    private double saldo;
    private double aposta;

    public Jogador(String id, String tipo) {
        this.id = id;
        this.tipo = tipo;
        this.saldo = 100.0; // Valor inicial
    }

    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setAposta(double aposta) {
        this.aposta = aposta;
    }

    public double getAposta() {
        return aposta;
    }

    public void ajustarSaldo(double valor) {
        saldo += valor;
    }
}