import java.util.Scanner;

public class simuladorTorneio {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Torneio torneio = new Torneio();

        while (true) {
            System.out.println("Menu:");
            System.out.println("(1) Incluir jogador");
            System.out.println("(2) Remover jogador");
            System.out.println("(3) Iniciar torneio");
            System.out.println("(4) Placar do torneio");
            System.out.println("(5) Gravar torneio em arquivo");
            System.out.println("(6) Ler torneio de arquivo");
            System.out.println("(7) Sair");
            System.out.print("Escolha uma opção: ");

            int opcao;
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Digite o ID do jogador: ");
                    String id = scanner.nextLine();
                    System.out.print("Jogador é humano? (0 - máquina / 1 - humano): ");
                    int tipo;
                    try {
                        tipo = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Por favor, insira 0 ou 1.");
                        continue;
                    }

                    Jogador jogador = new Jogador(id, tipo == 1);
                    torneio.incluirJogador(jogador);
                    break;
                case 2:
                    System.out.print("Digite o ID do jogador a ser removido: ");
                    String idRemover = scanner.nextLine();
                    torneio.removerJogador(idRemover);
                    break;
                case 3:
                    System.out.println("Escolha o jogo para o torneio:");
                    System.out.println("(0) Jogo de Azar");
                    System.out.println("(1) Jogo do Porquinho");
                    int jogoEscolhido;
                    try {
                        jogoEscolhido = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Por favor, insira 0 ou 1.");
                        continue;
                    }

                    torneio.escolherJogo(jogoEscolhido);
                    torneio.iniciarTorneio(scanner);
                    break;
                case 4:
                    torneio.placarTorneio();
                    break;
                case 5:
                    torneio.gravarEmArquivo();
                    break;
                case 6:
                    torneio.lerDoArquivo();
                    break;
                case 7:
                    System.out.println("Encerrando o programa.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}
