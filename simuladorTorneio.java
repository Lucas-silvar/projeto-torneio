import java.util.Scanner;

public class simuladorTorneio {

    public static void main(String[] args) {
        Torneio torneio = new Torneio();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("(1) Incluir jogador");
            System.out.println("(2) Remover jogador");
            System.out.println("(3) Iniciar torneio");
            System.out.println("(4) Placar do torneio");
            System.out.println("(7) Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consome a nova linha

            switch (opcao) {
                case 1:
                    System.out.print("Digite o ID do jogador: ");
                    String id = scanner.nextLine();
                    System.out.print("Jogador humano ou máquina? ");
                    String tipo = scanner.nextLine();
                    Jogador jogador = new Jogador(id, tipo);
                    torneio.incluirJogador(jogador);
                    break;
                case 2:
                    System.out.print("Digite o ID do jogador a ser removido: ");
                    String idRemover = scanner.nextLine();
                    torneio.removerJogador(idRemover);
                    break;
                case 3:
                    torneio.iniciarTorneio();
                    break;
                case 4:
                    torneio.placarTorneio();
                    break;
                case 7:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
