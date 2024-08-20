import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Torneio {
    private List<Jogador> jogadores;
    private int jogoEscolhido;

    public Torneio(int capacidade) {
        jogadores = new ArrayList<>(capacidade);
        jogoEscolhido = -1;
    }

    public void incluirJogador(Jogador jogador) {
        jogadores.add(jogador);
        System.out.println("Jogador " + jogador.getId() + " foi incluído.");
    }

    public void removerJogador(String id) {
        jogadores.removeIf(jogador -> jogador.getId().equals(id));
        System.out.println("Jogador " + id + " foi removido.");
    }

    public void escolherJogo(int jogo) {
        this.jogoEscolhido = jogo;
        System.out.println("Jogo escolhido para o torneio: " + jogoEscolhido);
    }

    public void iniciarTorneio(Scanner scanner) {
        if (jogadores.size() < 2) {
            System.out.println("É necessário pelo menos 2 jogadores para iniciar o torneio.");
            return;
        }

        // Restabelece o saldo dos jogadores
        for (Jogador jogador : jogadores) {
            jogador.setSaldo(100); // Define o saldo inicial de 100 moedas
        }

        while (jogadores.size() > 1) {
            // Executa as rodadas do torneio
            while (jogadores.size() > 1) {
                executarRodada(scanner);
            }
        }

        if (jogadores.size() == 1) {
            Jogador vencedor = jogadores.get(0);
            System.out.printf("O vencedor do torneio é %s com um saldo de %.2f%n", vencedor.getId(), vencedor.getSaldo());
        } else {
            System.out.println("Não há um vencedor único. O torneio terminou sem um vencedor.");
        }
    }
    private void executarRodada(Scanner scanner) {
        double totalApostas = 0;
        Jogador vencedorPorquinho = null;
        int menorNumeroDeLancamentos = Integer.MAX_VALUE;
        double maiorAposta = -1;
        Jogador vencedorAzar = null;

        for (Jogador jogador : jogadores) {
            if (jogador.getSaldo() <= 0) {
                continue;
            }

            double aposta;
            if (jogador.isHumano()) {
                System.out.printf("Saldo atual de %s: %.2f%n", jogador.getId(), jogador.getSaldo());
                while (true) {
                    System.out.print("Jogador " + jogador.getId() + ", informe o valor que deseja apostar: ");
                    aposta = scanner.nextDouble();
                    scanner.nextLine(); // Limpa o buffer
                    if (aposta > 0 && aposta <= jogador.getSaldo()) {
                        break;
                    } else {
                        System.out.println("Valor de aposta inválido. Deve ser positivo e não maior que o saldo.");
                    }
                }
            } else {
                aposta = jogador.getSaldo() / 5; // Máquinas apostam 1/5 do saldo
                System.out.printf("Jogador %s (máquina) - Saldo: %.2f, Aposta: %.2f%n", jogador.getId(), jogador.getSaldo(), aposta);
            }

            jogador.setAposta(aposta);
            jogador.ajustarSaldo(-aposta);
            totalApostas += aposta;

            switch (jogoEscolhido) {
                case 0: // Jogo de Azar
                    boolean ganhou = new JogoDados().jogoAzar(jogador);
                    if (ganhou) {
                        vencedorAzar = jogador;
                    }
                    break;

                case 1: // Jogo do Porquinho
                    double pontos = new JogoDados().jogoPorquinho(jogador);
                    int lancamentos = jogador.getNumeroDeJogadas();

                    if (pontos >= 300) {
                        if (lancamentos < menorNumeroDeLancamentos ||
                                (lancamentos == menorNumeroDeLancamentos && aposta > maiorAposta)) {
                            vencedorPorquinho = jogador;
                            menorNumeroDeLancamentos = lancamentos;
                            maiorAposta = aposta;
                        }
                    }
                    break;
            }
        }

        // Verificação do vencedor para o jogo do Porquinho
        if (jogoEscolhido == 1 && vencedorPorquinho != null) {
            vencedorPorquinho.ajustarSaldo(totalApostas);
            System.out.println("Vencedor da rodada: " + vencedorPorquinho.getId() + " com saldo de " + vencedorPorquinho.getSaldo());
        } else if (jogoEscolhido == 1) {
            System.out.println("Nenhum jogador atingiu 300 pontos ou houve empate sem vencedor.");
        }

        // Verificação do vencedor para o jogo de azar
        if (jogoEscolhido == 0 && vencedorAzar != null) {
            vencedorAzar.ajustarSaldo(totalApostas);
            System.out.println("Vencedor da rodada: " + vencedorAzar.getId() + " com saldo de " + vencedorAzar.getSaldo());
        }

        // Relatório da rodada
        System.out.println("Relatório da Rodada:");
        List<Jogador> jogadoresParaRemover = new ArrayList<>();
        for (Jogador jogador : jogadores) {
            if (jogador.getSaldo() <= 0) {
                jogador.setSaldo(0);
                System.out.println("Jogador " + jogador.getId() + " foi eliminado por saldo insuficiente.");
                jogadoresParaRemover.add(jogador);
            } else {
                System.out.printf("Jogador %s - Aposta: %.2f - Saldo Atual: %.2f%n", jogador.getId(), jogador.getAposta(), jogador.getSaldo());
            }
        }
        jogadores.removeAll(jogadoresParaRemover);

        // Separador de relatório
        System.out.println("--------------------------------------------------");
    }

    public void placarTorneio() {
        System.out.println("Placar do Torneio:");
        for (Jogador jogador : jogadores) {
            System.out.println(jogador);
        }
    }
}