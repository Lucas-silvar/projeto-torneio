import java.util.Random;

public class Torneio {
    private Jogador[] jogadores;
    private int numeroDeJogadores;
    private JogoDados jogoDados;

    public Torneio() {
        jogadores = new Jogador[10];
        numeroDeJogadores = 0;
        jogoDados = new JogoDados();
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

    public void iniciarTorneio() {
        if (numeroDeJogadores < 2) {
            System.out.println("Número insuficiente de jogadores.");
            return;
        }

        for (int rodada = 1; rodada <= 5; rodada++) {
            System.out.println("Rodada " + rodada + " começa agora!");

            for (int i = 0; i < numeroDeJogadores; i++) {
                Jogador jogador = jogadores[i];
                double aposta = jogador.getSaldo() / 10; // Exemplo de aposta: 10% do saldo
                jogador.setAposta(aposta);

                System.out.printf("%s apostou %.2f moedas.%n", jogador.getId(), aposta);

                // Escolhe aleatoriamente dois jogos diferentes para cada jogador
                int[] tiposDeJogo = {0, 1, 2};
                shuffle(tiposDeJogo);
                boolean ganhou;

                for (int tipoDeJogo : tiposDeJogo) {
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

                    if (ganhou) {
                        jogador.ajustarSaldo(aposta);
                        System.out.printf("%s ganhou a rodada! +%.2f moedas.%n", jogador.getId(), aposta);
                    } else {
                        jogador.ajustarSaldo(-aposta);
                        System.out.printf("%s perdeu a rodada. -%.2f moedas.%n", jogador.getId(), aposta);
                    }

                    // Atualiza o saldo do adversário
                    for (int j = 0; j < numeroDeJogadores; j++) {
                        if (j != i) {
                            jogadores[j].ajustarSaldo(-aposta);
                        }
                    }
                }
            }
        }

        System.out.println("Torneio concluído!");
    }

    public void placarTorneio() {
        System.out.println("Placar do torneio:");
        for (int i = 0; i < numeroDeJogadores; i++) {
            Jogador jogador = jogadores[i];
            System.out.printf("%s - Saldo: %.2f%n", jogador.getId(), jogador.getSaldo());
        }
    }

    private void shuffle(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}
