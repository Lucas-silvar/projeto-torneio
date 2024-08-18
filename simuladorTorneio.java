import java.util.InputMismatchException;
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
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine();
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
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Por favor, insira 0 ou 1.");
                        scanner.nextLine();
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
                    int jogoEscolhido = escolherJogo(scanner);
                    if (jogoEscolhido != -1) {
                        torneio.escolherJogo(jogoEscolhido);
                        torneio.iniciarTorneio(scanner); // Passando o scanner para coletar apostas
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

    private static int escolherJogo(Scanner scanner) {
        System.out.println("Escolha o jogo para o torneio:");
        System.out.println("(0) Jogo de Azar");
        System.out.println("(1) Bozó");
        System.out.println("(2) Jogo do Porquinho");

        int escolha = -1;
        try {
            escolha = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, insira um número entre 0 e 2.");
            scanner.nextLine();
            return -1;
        }

        if (escolha < 0 || escolha > 2) {
            System.out.println("Opção inválida.");
            return -1;
        }

        return escolha;
    }
}
//