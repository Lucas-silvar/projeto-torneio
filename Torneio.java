import java.io.*;
import java.util.*;

public class Torneio implements Serializable {
    private Jogador[] jogadores; // Array que armazena os jogadores participantes do torneio
    private int numJogadores; // Contador para o número atual de jogadores
    private int jogoEscolhido; // Identificador do jogo escolhido para o torneio
    public static final int MAX_JOGADORES = 10; // Constante que define a capacidade máxima de jogadores

    private static final long serialVersionUID = 1L; // Identificador da versão da serialização

    // Construtor da classe Torneio
    public Torneio(int capacidade) {
        jogadores = new Jogador[capacidade]; // Inicializa o array de jogadores com a capacidade fornecida
        numJogadores = 0; // Inicializa o número de jogadores como zero
        jogoEscolhido = -1; // Inicializa o jogo escolhido como não definido
    }

    // Adiciona um novo jogador ao torneio
    public void incluirJogador(Jogador jogador) {
        if (numJogadores < MAX_JOGADORES) { // Verifica se ainda há espaço para mais jogadores
            jogadores[numJogadores++] = jogador; // Adiciona o jogador e incrementa o contador
            System.out.println("Jogador " + jogador.getId() + " foi incluído.");
        } else {
            System.out.println("Capacidade máxima de jogadores atingida."); // Mensagem de erro se a capacidade for atingida
        }
    }

    // Remove um jogador do torneio com base no ID
    public void removerJogador(String id) {
        for (int i = 0; i < numJogadores; i++) { // Itera sobre os jogadores para encontrar o jogador com o ID fornecido
            if (jogadores[i].getId().equals(id)) { // Verifica se o ID do jogador corresponde
                for (int j = i; j < numJogadores - 1; j++) { // Move todos os jogadores subsequentes para preencher o espaço
                    jogadores[j] = jogadores[j + 1];
                }
                jogadores[--numJogadores] = null; // Define o último espaço como null e decrementa o contador
                System.out.println("Jogador " + id + " foi removido.");
                return;
            }
        }
        System.out.println("Jogador com ID " + id + " não encontrado."); // Mensagem de erro se o jogador não for encontrado
    }

    // Define o jogo escolhido para o torneio
    public void escolherJogo(int jogo) {
        this.jogoEscolhido = jogo; // Atualiza o jogo escolhido
        System.out.println("Jogo escolhido para o torneio: " + (jogo == 0 ? "Jogo de Azar" : "Jogo do Porquinho"));
    }

    // Inicia o torneio com base no jogo escolhido
    public void iniciarTorneio(Scanner scanner) {
        if (numJogadores < 2) { // Verifica se há pelo menos dois jogadores
            System.out.println("É necessário pelo menos 2 jogadores para iniciar o torneio.");
            return;
        }

        // Restabelece o saldo dos jogadores
        for (int i = 0; i < numJogadores; i++) {
            jogadores[i].setSaldo(100); // Define o saldo inicial de 100 moedas para cada jogador
        }

        while (numJogadores > 1) { // Executa as rodadas enquanto houver mais de um jogador
            executarRodada(scanner); // Executa uma rodada do torneio
        }

        if (numJogadores == 1) { // Verifica se há um vencedor
            Jogador vencedor = jogadores[0];
            System.out.printf("O vencedor do torneio é %s com um saldo de %.2f%n", vencedor.getId(), vencedor.getSaldo());
        } else {
            System.out.println("Não há um vencedor único. O torneio terminou sem um vencedor.");
        }
    }

    // Executa uma rodada do torneio
    private void executarRodada(Scanner scanner) {
        double totalApostas = 0; // Inicializa o total de apostas na rodada
        Jogador vencedorPorquinho = null; // Inicializa o vencedor do Jogo do Porquinho
        int menorNumeroDeLancamentos = Integer.MAX_VALUE; // Inicializa o menor número de lançamentos como o máximo valor possível
        double maiorAposta = -1; // Inicializa a maior aposta como um valor negativo
        Jogador vencedorAzar = null; // Inicializa o vencedor do Jogo de Azar

        for (int i = 0; i < numJogadores; i++) {
            Jogador jogador = jogadores[i];
            if (jogador.getSaldo() <= 0) { // Pular jogadores com saldo insuficiente
                continue;
            }

            double aposta;
            if (jogador.isHumano()) { // Se o jogador for humano
                System.out.printf("Saldo atual de %s: %.2f%n", jogador.getId(), jogador.getSaldo());
                while (true) {
                    System.out.print("Jogador " + jogador.getId() + ", informe o valor que deseja apostar: ");
                    aposta = scanner.nextDouble();
                    scanner.nextLine(); // Limpa o buffer
                    if (aposta > 0 && aposta <= jogador.getSaldo()) { // Verifica se a aposta é válida
                        break;
                    } else {
                        System.out.println("Valor de aposta inválido. Deve ser positivo e não maior que o saldo.");
                    }
                }
            } else { // Se o jogador for uma máquina
                aposta = jogador.getSaldo() / 5; // Máquinas apostam 1/5 do saldo
                System.out.printf("Jogador %s (máquina) - Saldo: %.2f, Aposta: %.2f%n", jogador.getId(), jogador.getSaldo(), aposta);
            }

            jogador.setAposta(aposta); // Define a aposta do jogador
            jogador.ajustarSaldo(-aposta); // Ajusta o saldo do jogador com base na aposta
            totalApostas += aposta; // Adiciona a aposta ao total

            // Determina o vencedor com base no jogo escolhido
            switch (jogoEscolhido) {
                case 0: // Jogo de Azar
                    boolean ganhou = new JogoDados().jogoAzar(jogador); // Executa o Jogo de Azar
                    if (ganhou) {
                        vencedorAzar = jogador; // Define o jogador como vencedor se ganhar
                    }
                    break;

                case 1: // Jogo do Porquinho
                    double pontos = new JogoDados().jogoPorquinho(jogador); // Executa o Jogo do Porquinho
                    int lancamentos = jogador.getNumeroDeJogadas();

                    // Verifica se o jogador atingiu a pontuação mínima e se é o melhor vencedor
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
            vencedorPorquinho.ajustarSaldo(totalApostas); // Adiciona o total das apostas ao saldo do vencedor
            System.out.println("Vencedor da rodada: " + vencedorPorquinho.getId() + " com saldo de " + vencedorPorquinho.getSaldo());
        } else if (jogoEscolhido == 1) {
            System.out.println("Nenhum jogador atingiu 300 pontos ou houve empate sem vencedor.");
        }

        // Verificação do vencedor para o jogo de azar
        if (jogoEscolhido == 0 && vencedorAzar != null) {
            vencedorAzar.ajustarSaldo(totalApostas); // Adiciona o total das apostas ao saldo do vencedor
            System.out.println("Vencedor da rodada: " + vencedorAzar.getId() + " com saldo de " + vencedorAzar.getSaldo());
        }

        // Relatório da rodada
        System.out.println("Relatório da Rodada:");
        List<Jogador> jogadoresParaRemover = new ArrayList<>(); // Lista para armazenar jogadores a serem removidos

        for (int i = 0; i < numJogadores; i++) {
            Jogador jogador = jogadores[i];
            if (jogador.getSaldo() <= 0) { // Se o saldo do jogador é zero ou negativo
                jogador.setSaldo(0); // Define o saldo como zero
                System.out.println("Jogador " + jogador.getId() + " foi eliminado por saldo insuficiente.");
                jogadoresParaRemover.add(jogador); // Adiciona o jogador à lista de remoção
            } else {
                System.out.printf("Jogador %s - Aposta: %.2f - Saldo Atual: %.2f%n", jogador.getId(), jogador.getAposta(), jogador.getSaldo());
            }
        }

        // Remove jogadores com saldo insuficiente
        for (Jogador jogador : jogadoresParaRemover) {
            removerJogador(jogador.getId());
        }

        // Separador de relatório
        System.out.println("--------------------------------------------------");
    }

    // Exibe o placar do torneio
    public void placarTorneio() {
        System.out.println("Placar do Torneio:");
        for (int i = 0; i < numJogadores; i++) {
            System.out.println(jogadores[i]);
        }
    }

    // Grava o estado do torneio em um arquivo
    public void gravarTorneio(String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(this); // Serializa o objeto Torneio
            System.out.println("Torneio gravado com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao gravar o torneio: " + e.getMessage()); // Mensagem de erro em caso de falha
        }
    }

    // Lê o estado do torneio a partir de um arquivo
    public static Torneio lerTorneio(String nomeArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return (Torneio) ois.readObject(); // Desserializa o objeto Torneio
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler o torneio: " + e.getMessage()); // Mensagem de erro em caso de falha
            return null;
        }
    }

    // Retorna o jogo escolhido para o torneio
    public int getJogoEscolhido() {
        return jogoEscolhido;
    }
}
