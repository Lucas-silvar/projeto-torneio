import java.util.Scanner;

public class simuladorTorneio {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Torneio torneio = new Torneio(10); // Capacidade de 10 jogadores

        while (true) {
            System.out.println("Menu:");
            System.out.println("(1) Incluir jogador");
            System.out.println("(2) Remover jogador");
            System.out.println("(3) Iniciar torneio");
            System.out.println("(4) Placar do torneio");
            System.out.println("(7) Sair");
            System.out.print("Escolha uma opção: ");

            int opcao;
            try {
                opcao = Integer.parseInt(scanner.nextLine());  // Usa nextLine e parse para evitar problemas de buffer
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                continue;  // Volta para o início do loop
            }

            switch (opcao) {
                case 1:
                    System.out.print("Digite o ID do jogador: ");
                    String id = scanner.nextLine();
                    System.out.print("Jogador é humano? (0 - máquina / 1 - humano): ");
                    int tipo;
                    try {
                        tipo = Integer.parseInt(scanner.nextLine());  // Usa nextLine e parse para evitar problemas de buffer
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Por favor, insira 0 ou 1.");
                        continue;  // Volta para o início do loop
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
                        jogoEscolhido = Integer.parseInt(scanner.nextLine());  // Usa nextLine e parse para evitar problemas de buffer
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Por favor, insira 0 ou 1.");
                        continue;  // Volta para o início do loop
                    }

                    torneio.escolherJogo(jogoEscolhido);
                    torneio.iniciarTorneio(scanner);
                    break;
                case 4:
                    torneio.placarTorneio();
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