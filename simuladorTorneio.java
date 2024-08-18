import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class simuladorTorneio {

    public static void main(String[] args) {
        Torneio torneio = new Torneio();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("(1) Incluir jogador");
            System.out.println("(2) Remover jogador");
            System.out.println("(3) Iniciar torneio");
            System.out.println("(4) Placar do torneio");
            System.out.println("(7) Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = -1;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a nova linha
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine(); // Limpa o buffer do scanner
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Digite o ID do jogador: ");
                    String id = scanner.nextLine();
                    System.out.print("Jogador é humano? (0 - máquina / 1 - humano): ");
                    int tipo = -1;
                    try {
                        tipo = scanner.nextInt();
                        scanner.nextLine(); // Consome a nova linha
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Por favor, insira 0 ou 1.");
                        scanner.nextLine(); // Limpa o buffer do scanner
                        continue;
                    }
                    Jogador jogador = new Jogador(id, tipo == 1 ? "humano" : "máquina");
                    torneio.incluirJogador(jogador);
                    break;
                case 2:
                    System.out.print("Digite o ID do jogador a ser removido: ");
                    String idRemover = scanner.nextLine();
                    torneio.removerJogador(idRemover);
                    break;
                case 3:
                    List<Integer> jogosEscolhidos = escolherJogos(scanner);
                    if (jogosEscolhidos.size() >= 2) {
                        torneio.escolherJogos(jogosEscolhidos);
                        torneio.iniciarTorneio();
                    } else {
                        System.out.println("Você deve escolher pelo menos 2 jogos.");
                    }
                    break;
                case 4:
                    torneio.placarTorneio();
                    break;
                case 7:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static List<Integer> escolherJogos(Scanner scanner) {
        List<Integer> jogosEscolhidos = new ArrayList<>();

        System.out.println("Escolha os jogos que deseja jogar:");
        System.out.println("(1) Jogo de Azar e Jogo do Porquinho");
        System.out.println("(2) Jogo de Azar e Bozó");
        System.out.println("(3) Bozó e Jogo do Porquinho");
        System.out.println("(4) Jogo do Porquinho, Jogo de Azar e Bozó");

        int escolha = -1;
        try {
            escolha = scanner.nextInt();
            scanner.nextLine(); // Consome a nova linha
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, insira um número entre 1 e 4.");
            scanner.nextLine(); // Limpa o buffer do scanner
            return jogosEscolhidos;
        }

        switch (escolha) {
            case 1:
                jogosEscolhidos.add(0); // Jogo de Azar
                jogosEscolhidos.add(2); // Jogo do Porquinho
                break;
            case 2:
                jogosEscolhidos.add(0); // Jogo de Azar
                jogosEscolhidos.add(1); // Bozó
                break;
            case 3:
                jogosEscolhidos.add(1); // Bozó
                jogosEscolhidos.add(2); // Jogo do Porquinho
                break;
            case 4:
                jogosEscolhidos.add(0); // Jogo de Azar
                jogosEscolhidos.add(1); // Bozó
                jogosEscolhidos.add(2); // Jogo do Porquinho
                break;
            default:
                System.out.println("Opção inválida. Nenhum jogo foi escolhido.");
        }

        return jogosEscolhidos;
    }
}
