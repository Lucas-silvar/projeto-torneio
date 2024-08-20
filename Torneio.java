import java.util.Scanner;

public class Torneio {
    private Jogador[] jogadores;
    private int numJogadores;
    private int capacidade;
    private int jogoEscolhido;

    public Torneio(int capacidade) {
        this.capacidade = capacidade;
        jogadores = new Jogador[capacidade];
        numJogadores = 0;
        jogoEscolhido = -1;
    }

    public void incluirJogador(Jogador jogador) {
        if (numJogadores < capacidade) {
            jogadores[numJogadores++] = jogador;
            System.out.println("Jogador " + jogador.getId() + " foi incluído.");
        } else {
            System.out.println("Não é possível incluir mais jogadores. Capacidade máxima atingida.");
        }
    }

    public void removerJogador(String id) {
        for (int i = 0; i < numJogadores; i++) {
            if (jogadores[i].getId().equals(id)) {
                // Move o último jogador para a posição atual
                jogadores[i] = jogadores[--numJogadores];
                jogadores[numJogadores] = null;
                System.out.println("Jogador " + id + " foi removido.");
                return;
            }
        }
        System.out.println("Jogador " + id + " não encontrado.");
    }

    public void escolherJogo(int jogo) {
        this.jogoEscolhido = jogo;
        System.out.println("Jogo escolhido para o torneio: " + jogoEscolhido);
    }

    public void iniciarTorneio(Scanner scanner) {
        if (numJogadores < 2) {
            System.out.println("É necessário pelo menos 2 jogadores para iniciar o torneio.");
            return;
        }

        // Restabelece o saldo dos jogadores
        for (int i = 0; i < numJogadores; i++) {
            jogadores[i].setSaldo(100); // Define o saldo inicial de 100 moedas
        }

        while (numJogadores > 1) {
            // Executa as rodadas do torneio
            while (numJogadores > 1) {
                executarRodada(scanner);
            }
        }

        if (numJogadores == 1) {
            Jogador vencedor = jogadores[0];
            System.out.printf("O vencedor do torneio é %s com um saldo de %.2f%n", vencedor.getId(), vencedor.getSaldo());
        } else {
            System.out.println("Não há um vencedor único. O torneio terminou sem um vencedor.");
        }
    }

    private void executarRodada(Scanner scanner) {
        double[] apostas = new double[numJogadores];
        boolean[] resultados = new boolean[numJogadores];
        double[] premios = new double[numJogadores];
        double totalApostas = 0;
        double maiorRetorno = -1;
        int[] vencedores = new int[numJogadores];
        int numVencedores = 0;

        // Coleta apostas e executa o jogo para cada jogador
        for (int i = 0; i < numJogadores; i++) {
            Jogador jogador = jogadores[i];

            if (jogador.getSaldo() <= 0) {
                continue;
            }

            double aposta;
            if (jogador.isMaquina()) {
                aposta = jogador.getSaldo() / 5;
            } else {
                while (true) {
                    System.out.print("Jogador " + jogador.getId() + ", informe o valor que deseja apostar: ");
                    aposta = scanner.nextDouble();
                    scanner.nextLine();
                    if (aposta > 0 && aposta <= jogador.getSaldo()) {
                        break;
                    } else {
                        System.out.println("Valor de aposta inválido. Deve ser positivo e não maior que o saldo.");
                    }
                }
            }

            jogador.setAposta(aposta);
            jogador.ajustarSaldo(-aposta);
            apostas[i] = aposta;
            totalApostas += aposta;

            boolean ganhou = false;
            double premio = 0;

            switch (jogoEscolhido) {
                case 0:
                    ganhou = new JogoDados().jogoAzar(jogador);
                    break;
                case 1:
                    premio = new JogoDados().jogoPorquinho(jogador);
                    break;
            }

            if (ganhou) {
                premios[i] = aposta;
                if (premio > 0) {
                    premios[i] += premio;
                }

                if (premios[i] > maiorRetorno) {
                    maiorRetorno = premios[i];
                    numVencedores = 0;
                }

                if (premios[i] == maiorRetorno) {
                    vencedores[numVencedores++] = i;
                }
            }
        }

        // Distribui as apostas dos oponentes para os vencedores
        if (numVencedores > 0) {
            double valorParaCada = totalApostas / numVencedores;
            for (int i = 0; i < numVencedores; i++) {
                int vencedorIndex = vencedores[i];
                jogadores[vencedorIndex].ajustarSaldo(valorParaCada);
            }
        }

        // Atualiza o saldo dos jogadores e remove os que têm saldo zero
        System.out.println("Relatório da Rodada:");
        for (int i = 0; i < numJogadores; i++) {
            Jogador jogador = jogadores[i];
            if (jogador.getSaldo() <= 0) {
                jogador.setSaldo(0);
                System.out.println("Jogador " + jogador.getId() + " foi eliminado por saldo insuficiente.");
                removerJogador(jogador.getId());
                i--; // Ajusta o índice após a remoção
            } else {
                System.out.printf("Jogador %s - Aposta: %.2f - Saldo Atual: %.2f%n", jogador.getId(), apostas[i], jogador.getSaldo());
            }
        }
    }

    public void placarTorneio() {
        System.out.println("Placar do Torneio:");
        for (int i = 0; i < numJogadores; i++) {
            System.out.println(jogadores[i]);
        }
    }
}
