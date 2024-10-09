import java.io.*;
import java.util.*;

public class Torneio implements Serializable {
    private Jogador[] jogadores; // Array de jogadores no torneio
    private int numJogadores; // Número atual de jogadores
    private int jogoEscolhido; // Tipo de jogo escolhido para o torneio
    public static final int MAX_JOGADORES = 10; // Número máximo de jogadores

    private static final long serialVersionUID = 1L;

    public Torneio(int capacidade) {
        jogadores = new Jogador[capacidade];
        numJogadores = 0;
        jogoEscolhido = -1;
    }

    public void incluirJogador(Jogador jogador) {
        if (numJogadores < MAX_JOGADORES) {
            jogadores[numJogadores++] = jogador;
            System.out.println("Jogador " + jogador.getId() + " foi incluído.");
        } else {
            System.out.println("Capacidade máxima de jogadores atingida.");
        }
    }

    public void removerJogador(String id) {
        for (int i = 0; i < numJogadores; i++) {
            if (jogadores[i].getId().equals(id)) {
                for (int j = i; j < numJogadores - 1; j++) {
                    jogadores[j] = jogadores[j + 1];
                }
                jogadores[--numJogadores] = null;
                System.out.println("Jogador " + id + " foi removido.");
                return;
            }
        }
        System.out.println("Jogador com ID " + id + " não encontrado.");
    }

    public void escolherJogo(int jogo) {
        this.jogoEscolhido = jogo;
        System.out.println("Jogo escolhido para o torneio: " + (jogo == 0 ? "Jogo de Azar" : "Jogo do Porquinho"));
    }

    public void iniciarTorneio(Scanner scanner) {
        if (numJogadores < 2) {
            System.out.println("É necessário pelo menos 2 jogadores para iniciar o torneio.");
            return;
        }

        // Implementar lógica de torneio de acordo com o jogo escolhido
        if (jogoEscolhido == 0) {
            // Implementar lógica do Jogo de Azar
            System.out.println("Iniciando o Jogo de Azar...");
            // Aqui você pode implementar a lógica do jogo
        } else if (jogoEscolhido == 1) {
            // Implementar lógica do Jogo do Porquinho
            System.out.println("Iniciando o Jogo do Porquinho...");
            // Aqui você pode implementar a lógica do jogo
        } else {
            System.out.println("Jogo escolhido inválido.");
        }
    }

    public void placarTorneio() {
        System.out.println("Placar do Torneio:");
        for (int i = 0; i < numJogadores; i++) {
            System.out.println(jogadores[i]);
        }
    }

    public void gravarTorneio(String nomeArquivo) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            out.writeObject(this);
            System.out.println("Torneio gravado com sucesso no arquivo " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao gravar o torneio: " + e.getMessage());
        }
    }

    public static Torneio lerTorneio(String nomeArquivo) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return (Torneio) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao ler o torneio: " + e.getMessage());
            return null;
        }
    }

    public int getJogoEscolhido() {
        return jogoEscolhido;
    }
}
