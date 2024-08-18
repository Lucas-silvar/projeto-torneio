import java.util.ArrayList;
import java.util.List;

public class Torneio {
    private Jogador[] jogadores;
    private int numeroDeJogadores;
    private JogoDados jogoDados;
    private List<Integer> jogosEscolhidos;

    public Torneio() {
        jogadores = new Jogador[10];
        numeroDeJogadores = 0;
        jogoDados = new JogoDados();
        jogosEscolhidos = new ArrayList<>();
    }

    public void incluirJogador(Jogador jogador) {
        if (numeroDeJogadores < 10) {
            jogadores[numeroDeJogadores] = jogador;
            numeroDeJogadores++;
        } else {
            System.out.println("Máximo de jogadores atingido.");
        }
    }

    public void removerJogador(String id) {
        for (int i = 0; i < numeroDeJogadores; i++) {
            if (jogadores[i].getId().equals(id)) {
                jogadores[i] = jogadores[numeroDeJogadores - 1];
                jogadores[numeroDeJogadores - 1] = null;
                numeroDeJogadores--;
                return;
            }
        }
        System.out.println("Jogador não encontrado.");
    }

    public void escolherJogos(List<Integer> jogosEscolhidos) {
        this.jogosEscolhidos.clear();
        this.jogosEscolhidos.addAll(jogosEscolhidos);
    }

    public void iniciarTorneio() {
        if (numeroDeJogadores < 2) {
            System.out.println("Número insuficiente de jogadores.");
            return;
        }

        for (int rodada = 1; rodada <= 5; rodada++) {
            System.out.println("Rodada " + rodada + " começa agora!");

            // Armazenar as apostas e resultados dos jogadores
            double[] apostas = new double[numeroDeJogadores];
            double[] resultados = new double[numeroDeJogadores];

            // Calcular apostas e jogar
            for (int i = 0; i < numeroDeJogadores; i++) {
                Jogador jogador = jogadores[i];
                double aposta = jogador.getSaldo() / 10; // Exemplo de aposta: 10% do saldo
                apostas[i] = aposta; // Armazenar aposta do jogador
                jogador.setAposta(aposta);

                System.out.printf("%s apostou %.2f moedas.%n", jogador.getId(), aposta);

                for (int tipoDeJogo : jogosEscolhidos) {
                    boolean ganhou = false;

                    switch (tipoDeJogo) {
                        case 0:
                            System.out.printf("%s está jogando Jogo de Azar.%n", jogador.getId());
                            ganhou = jogoDados.jogoAzar(jogador);
                            break;
                        case 1:
                            System.out.printf("%s está jogando Bozó.%n", jogador.getId());
                            double pontuacaoBozo = jogoDados.jogoBozo(jogador);
                            ganhou = pontuacaoBozo > 0;
                            break;
                        case 2:
                            System.out.printf("%s está jogando Jogo do Porquinho.%n", jogador.getId());
                            double pontosPorquinho = jogoDados.jogoPorquinho(jogador);
                            ganhou = pontosPorquinho >= 300;
                            break;
                        default:
                            continue;
                    }

                    // Ajustar o saldo do jogador baseado no resultado do jogo
                    if (ganhou) {
                        resultados[i] += aposta;
                        System.out.printf("%s ganhou a rodada! +%.2f moedas.%n", jogador.getId(), aposta);
                    } else {
                        resultados[i] -= aposta;
                        System.out.printf("%s perdeu a rodada. -%.2f moedas.%n", jogador.getId(), aposta);
                    }
                }
            }

            // Ajustar o saldo dos jogadores
            ajustarSaldoJogadores(resultados);
        }

        System.out.println("Torneio concluído!");
    }

    private void ajustarSaldoJogadores(double[] resultados) {
        double saldoTotal = 0;

        // Calcular o saldo total que deve ser redistribuído
        for (double resultado : resultados) {
            saldoTotal += resultado;
        }

        // Atualizar saldos dos jogadores
        for (int i = 0; i < numeroDeJogadores; i++) {
            Jogador jogador = jogadores[i];
            jogador.ajustarSaldo(resultados[i]);
        }
    }

    public void placarTorneio() {
        System.out.println("Placar do torneio:");
        for (int i = 0; i < numeroDeJogadores; i++) {
            Jogador jogador = jogadores[i];
            System.out.printf("%s - Saldo: %.2f%n", jogador.getId(), jogador.getSaldo());
        }
    }
}
