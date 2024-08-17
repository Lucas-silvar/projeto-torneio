import java.util.Arrays;

public class JogoDados {
    private Dado[] dados;
    private int qtdeDados;
    private String tipoJogo;

    public JogoDados(int qtdeDados, String tipoJogo) {
        this.qtdeDados = qtdeDados;
        this.tipoJogo = tipoJogo;
        this.dados = new Dado[qtdeDados];
        for (int i = 0; i < qtdeDados; i++) {
            dados[i] = new Dado();
        }
    }

    public void rolarDados() {
        for (Dado dado : dados) {
            dado.rolar();
        }
    }

    public boolean jogoAzar(Jogador jogador) {
        rolarDados();
        int soma = dados[0].getValor() + dados[1].getValor();
        System.out.println("Soma dos dados: " + soma);

        if (soma == 7 || soma == 11) {
            return true;
        } else if (soma == 2 || soma == 3 || soma == 12) {
            return false;
        }

        int valorMeta = soma;
        while (true) {
            rolarDados();
            soma = dados[0].getValor() + dados[1].getValor();
            System.out.println("Soma dos dados: " + soma);
            if (soma == valorMeta) {
                return true;
            } else if (soma == 2 || soma == 3 || soma == 12) {
                return false;
            }
        }
    }

    public int calcularPontuacaoBozo(Dado[] dados) {
        // Implementa a lógica para calcular a pontuação no jogo Bozó
        return 0;
    }

    public boolean jogoBozo(Jogador jogador) {
        int maxPontuacao = 0;
        for (int tentativa = 0; tentativa < 3; tentativa++) {
            jogador.rolarDados();
            int pontuacao = calcularPontuacaoBozo(jogador.getDados());
            if (pontuacao > maxPontuacao) {
                maxPontuacao = pontuacao;
            }
            System.out.println("Tentativa " + (tentativa + 1) + ": " + pontuacao + " pontos.");
        }
        return maxPontuacao >= 0; // Ajustar a lógica conforme necessário
    }

    public boolean jogoPorquinho(Jogador jogador) {
        int pontos = 0;
        while (pontos < 300) {
            rolarDados();
            int produto = dados[0].getValor() * dados[1].getValor();
            if (dados[0].getValor() == dados[1].getValor()) {
                produto *= 2; // Doble
            }
            pontos += produto;
            System.out.println("Pontos atuais: " + pontos);
            if (pontos >= 300) {
                return true;
            }
        }
        return false;
    }
}
