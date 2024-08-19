import java.util.Scanner;
import java.util.InputMismatchException;
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

        aplicarRegraJogo(scanner);
        System.out.println("Torneio finalizado.");
    }

    private void aplicarRegraJogo(Scanner scanner) {
        JogoDados jogoDados = new JogoDados();
        Jogador vencedor = null;

        while (numJogadores > 1) {
            for (int i = 0; i < numJogadores; i++) {
                Jogador jogador = jogadores[i];
                if (jogador.getSaldo() <= 0) {
                    continue;
                }

                System.out.printf("Jogador %s (%s) está jogando.%n", jogador.getId(), jogador.getTipo());
                System.out.printf("Saldo atual: %.2f%n", jogador.getSaldo());

                double aposta;
                if (jogador.isMaquina()) {
                    aposta = jogador.getSaldo() > 5 ? jogador.getSaldo() / 5 : jogador.getSaldo();
                } else {
                    while (true) {
                        System.out.print("Informe o valor que deseja apostar: ");
                        try {
                            aposta = scanner.nextDouble();
                            scanner.nextLine();
                            if (aposta > 0 && aposta <= jogador.getSaldo()) {
                                break;
                            } else {
                                System.out.println("Valor de aposta inválido. Deve ser positivo e não maior que o saldo.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Por favor, insira um número.");
                            scanner.nextLine();
                        }
                    }
                }

                jogador.setAposta(aposta);

                boolean ganhou = false;
                double premio = 0;

                switch (jogoEscolhido) {
                    case 0:
                        ganhou = jogoDados.jogoAzar(jogador);
                        break;
                    case 1:
                        premio = jogoDados.jogoPorquinho(jogador);
                        break;
                }

                if (ganhou || premio > 0) {
                    double valorGanho = premio > 0 ? premio : aposta;
                    jogador.ajustarSaldo(valorGanho);
                    System.out.printf("Jogador %s ganhou! Novo saldo: %.2f%n", jogador.getId(), jogador.getSaldo());
                } else {
                    jogador.ajustarSaldo(-aposta);
                    System.out.printf("Jogador %s perdeu. Novo saldo: %.2f%n", jogador.getId(), jogador.getSaldo());

                    if (jogador.getSaldo() <= 0) {
                        jogador.setSaldo(0);
                        System.out.println("Jogador " + jogador.getId() + " perdeu e seu saldo foi ajustado para zero.");
                        removerJogador(jogador.getId());
                        i--; // Ajusta o índice após a remoção
                    }
                }
            }
        }

        if (numJogadores == 1) {
            vencedor = jogadores[0];
            System.out.printf("O vencedor do torneio é %s com um saldo de %.2f%n", vencedor.getId(), vencedor.getSaldo());
        } else {
            System.out.println("Não há um vencedor único. O torneio terminou sem um vencedor.");
        }
    }

    public void placarTorneio() {
        System.out.println("Placar do Torneio:");
        for (int i = 0; i < numJogadores; i++) {
            System.out.println(jogadores[i]);
        }
    }
}