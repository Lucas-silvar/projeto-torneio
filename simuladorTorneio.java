import java.util.Scanner;

public class simuladorTorneio {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Torneio torneio = new Torneio();

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Incluir jogador");
            System.out.println("2. Remover jogador");
            System.out.println("3. Iniciar torneio");
            System.out.println("4. Placar do torneio");
            System.out.println("5. Gravar dados");
            System.out.println("6. Ler dados");
            System.out.println("7. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.print("Digite o ID do jogador: ");
                    String id = scanner.nextLine();
                    System.out.print("Digite o tipo do jogador (humano ou maquina): ");
                    String tipo = scanner.nextLine();
                    Jogador jogador = new Jogador(id, tipo);
                    System.out.print("Escolha o tipo de jogo (Azar, Bozo, Porquinho): ");
                    String tipoJogo = scanner.nextLine();
                    jogador.setJogo(tipoJogo);
                    torneio.adicionarJogador(jogador);
                    break;
                case 2:
                    System.out.print("Digite o ID do jogador para remover: ");
                    String idRemover = scanner.nextLine();
                    torneio.removerJogador(idRemover);
                    break;
                case 3:
                    torneio.iniciarTorneio();
                    break;
                case 4:
                    // Exibir placar
                    break;
                case 5:
                    // Gravar dados em arquivo
                    break;
                case 6:
                    // Ler dados de arquivo
                    break;
                case 7:
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
