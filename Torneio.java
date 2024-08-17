public class Torneio {
    private Jogador[] jogadores;
    private int numeroDeJogadores;
    private JogoDados jogoDados;

    public Torneio() {
        jogadores = new Jogador[10];
        numeroDeJogadores = 0;
        // Inicializa JogoDados com o número de dados e tipo de jogo
        jogoDados = new JogoDados(2, "Azar"); // Exemplo de configuração
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
                int aposta = jogador.getSaldo() / 10; // Exemplo de aposta: 10% do saldo
                jogador.setAposta(aposta);

                System.out.println(jogador.getId() + " apostou " + aposta + " moedas.");

                // Escolhe aleatoriamente um dos três jogos
                int tipoDeJogo = (int) (Math.random() * 3);
                boolean ganhou = false;

                switch (tipoDeJogo) {
                    case 0:
                        System.out.println(jogador.getId() + " está jogando Jogo de Azar.");
                        ganhou = jogoDados.jogoAzar(jogador);
                        break;
                    case 1:
                        System.out.println(jogador.getId() + " está jogando Bozó.");
                        ganhou = jogoDados.jogoBozo(jogador);
                        break;
                    case 2:
                        System.out.println(jogador.getId() + " está jogando Jogo do Porquinho.");
                        ganhou = jogoDados.jogoPorquinho(jogador);
                        break;
                }

                if (ganhou) {
                    jogador.ajustarSaldo(aposta);
                    System.out.println(jogador.getId() + " ganhou a rodada! + " + aposta + " moedas.");
                } else {
                    jogador.ajustarSaldo(-aposta);
                    System.out.println(jogador.getId() + " perdeu a rodada. - " + aposta + " moedas.");
                }
            }
        }
        System.out.println("Torneio concluído!");
        placarTorneio();
    }

    public void placarTorneio() {
        System.out.println("Placar do torneio:");
        for (int i = 0; i < numeroDeJogadores; i++) {
            System.out.println(jogadores[i].getId() + " - Saldo: " + jogadores[i].getSaldo());
        }
    }
}
