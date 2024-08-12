public class Jogador {
    private String id;
    private String tipo;
    private int saldo;
    private int aposta;
    private int resultado;
    private JogoDados.JogoAzar jogoAzar;
    private JogoDados.Bozo jogoBozo;
    private JogoDados.JogoPorquinho jogoPorquinho;

    public Jogador(String id, String tipo) {
        this.id = id;
        this.tipo = tipo;
        this.saldo = 100; // Saldo inicial
    }

    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setAposta(int aposta) {
        this.aposta = aposta;
    }

    public int getAposta() {
        return aposta;
    }

    public void atualizarSaldo(int valor) {
        this.saldo += valor;
    }

    public void setJogo(String tipoJogo) {
        switch (tipoJogo) {
            case "Azar":
                this.jogoAzar = new JogoDados.JogoAzar();
                break;
            case "Bozo":
                this.jogoBozo = new JogoDados.Bozo();
                break;
            case "Porquinho":
                this.jogoPorquinho = new JogoDados.JogoPorquinho();
                break;
            default:
                throw new IllegalArgumentException("Tipo de jogo desconhecido");
        }
    }

    public int jogar(Dado[] dados) {
        if (jogoAzar != null) {
            return jogoAzar.jogar(dados);
        } else if (jogoBozo != null) {
            return jogoBozo.jogar(dados);
        } else if (jogoPorquinho != null) {
            return jogoPorquinho.jogar(dados);
        } else {
            throw new IllegalStateException("Jogo não definido");
        }
    }
}
