import java.io.*;
import java.util.Scanner;

public class Torneio implements Serializable {
    private static final int MAX_JOGADORES = 10; // Número máximo de jogadores
    private Jogador[] jogadores;
    private int numeroJogadores;
    private int jogoEscolhido;

    public Torneio() {
        this.jogadores = new Jogador[MAX_JOGADORES];
        this.numeroJogadores = 0;
        this.jogoEscolhido = -1;
    }

    public void incluirJogador(Jogador jogador) {
        if (numeroJogadores < MAX_JOGADORES) {
            jogadores[numeroJogadores++] = jogador;
            System.out.println("Jogador " + jogador.getId() + " foi incluído.");
        } else {
            System.out.println("Número máximo de jogadores atingido.");
        }
    }

    public void removerJogador(String id) {
        for (int i = 0; i < numeroJogadores; i++) {
            if (jogadores[i].getId().equals(id)) {
                for (int j = i; j < numeroJogadores - 1; j++) {
                    jogadores[j] = jogadores[j + 1];
                }
                jogadores[--numeroJogadores] = null;
                System.out.println("Jogador " + id + " foi removido.");
                return;
            }
        }
        System.out.println("Jogador " + id + " não encontrado.");
    }

    public void escolherJogo(int jogo) {
        this.jogoEscolhido = jogo;
        System.out.println("Jogo escolhido para o torneio: " + (jogo == 0 ? "Jogo de Azar" : "Jogo do Porquinho"));
    }

    public void iniciarTorneio(Scanner scanner) {
        if (numeroJogadores < 2) {
            System.out.println("É necessário pelo menos 2 jogadores para iniciar o torneio.");
            return;
        }

        // Restabelece o saldo dos jogadores
        for (int i = 0; i < numeroJogadores; i++) {
            jogadores[i].setSaldo(100); // Define o saldo inicial de 100 moedas
        }

        while (numeroJogadores > 1) {
            // Executa as rodadas do torneio
            while (numeroJogadores > 1) {
                executarRodada(scanner);
            }
        }

        if (numeroJogadores == 1) {
            Jogador vencedor = jogadores[0];
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

        for (int i = 0; i < numeroJogadores; i++) {
            Jogador jogador = jogadores[i];
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
        int i = 1;
        Jogador[] jogadoresParaRemover = new Jogador[numeroJogadores];
        int count = 0;
        for (int j = 0; j < numeroJogadores; j++) {
            Jogador jogador = jogadores[j];
            if (jogador.getSaldo() <= 0) {
                jogador.setSaldo(0);
                System.out.println("Jogador " + jogador.getId() + " foi eliminado por saldo insuficiente.");
                jogadoresParaRemover[count++] = jogador;
            } else {
                System.out.printf("Jogador %s - Aposta: %.2f - Saldo Atual: %.2f%n", jogador.getId(), jogador.getAposta(), jogador.getSaldo());
            }
        }
        // Remove jogadores eliminados
        for (Jogador jogador : jogadoresParaRemover) {
            if (jogador != null) {
                removerJogador(jogador.getId());
            }
        }

        // Separador de relatório
        System.out.println("--------------------------------------------------");
    }

    public void placarTorneio() {
        System.out.println("Placar do Torneio:");
        for (int i = 0; i < numeroJogadores; i++) {
            System.out.println(jogadores[i]);
        }
    }

    public void gravarEmArquivo() {
        try {
            FileOutputStream fout = new FileOutputStream("Campeonato.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeInt(numeroJogadores);
            for (int i = 0; i < numeroJogadores; i++) {
                oos.writeObject(jogadores[i]);
            }
            oos.flush();
            oos.close();
            fout.close();
            System.out.println("Gravado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao gravar: " + e.getMessage());
        }
    }

    public void lerDoArquivo() {
        try {
            FileInputStream fin = new FileInputStream("Campeonato.dat");
            ObjectInputStream oin = new ObjectInputStream(fin);
            numeroJogadores = oin.readInt();
            jogadores = new Jogador[MAX_JOGADORES];
            for (int i = 0; i < numeroJogadores; i++) {
                jogadores[i] = (Jogador) oin.readObject();
            }
            oin.close();
            fin.close();
            System.out.println("Leitura concluída com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler: " + e.getMessage());
        }
    }
}
