public class Jogador {
    private String id;
    private String tipo; // Tipo de jogador: "humano" ou "máquina"
    private double saldo;
    private double aposta;

    public Jogador(String id, String tipo) {
        this.id = id;
        this.tipo = tipo;
        this.saldo = 100; // Saldo inicial padrão
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
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

    public boolean isMaquina() {
        return "máquina".equalsIgnoreCase(tipo);
    }

    public void ajustarSaldo(double valor) {
        this.saldo += valor;
    }

    public boolean podeApostar() {
        return saldo > 0;
    }

    public void apostar(double aposta) {
        setAposta(aposta);
        ajustarSaldo(-aposta);
    }

    @Override
    public String toString() {
        return "Jogador: " + id + " | Tipo: " + tipo + " | Saldo: " + saldo + " | Aposta: " + aposta;
    }
}