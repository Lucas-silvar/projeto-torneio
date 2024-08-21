import java.util.Scanner;

public class simuladorTorneio {
    public static void main(String[] args) {
        // Cria um objeto Scanner para leitura de entradas do usuário
        Scanner scanner = new Scanner(System.in);

        // Cria um objeto Torneio com capacidade máxima de jogadores definida por MAX_JOGADORES
        Torneio torneio = new Torneio(Torneio.MAX_JOGADORES);

        // Loop principal do programa
        while (true) {
            // Exibe o menu de opções para o usuário
            System.out.println("Menu:");
            System.out.println("(1) Incluir jogador");
            System.out.println("(2) Remover jogador");
            System.out.println("(3) Iniciar torneio");
            System.out.println("(4) Placar do torneio");
            System.out.println("(5) Gravar torneio");
            System.out.println("(6) Ler torneio");
            System.out.println("(7) Sair");
            System.out.print("Escolha uma opção: ");

            int opcao;
            try {
                // Lê a opção do usuário e converte para um número inteiro
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                // Se a entrada não for um número, exibe uma mensagem de erro e continua no loop
                System.out.println("Entrada inválida. Por favor, insira um número.");
                continue;
            }

            // Processa a opção escolhida pelo usuário
            switch (opcao) {
                case 1:
                    // Adiciona um novo jogador ao torneio
                    System.out.print("Digite o ID do jogador: ");
                    String id = scanner.nextLine();
                    System.out.print("Jogador é humano? (0 - máquina / 1 - humano): ");
                    int tipo;
                    try {
                        tipo = Integer.parseInt(scanner.nextLine()); // Lê e converte o tipo de jogador
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Por favor, insira 0 ou 1.");
                        continue;
                    }

                    // Cria um novo objeto Jogador e o adiciona ao torneio
                    Jogador jogador = new Jogador(id, tipo == 1);
                    torneio.incluirJogador(jogador);
                    break;

                case 2:
                    // Remove um jogador do torneio
                    System.out.print("Digite o ID do jogador a ser removido: ");
                    String idRemover = scanner.nextLine();
                    torneio.removerJogador(idRemover);
                    break;

                case 3:
                    // Inicia o torneio
                    System.out.println("Escolha o jogo para o torneio:");
                    System.out.println("(0) Jogo de Azar");
                    System.out.println("(1) Jogo do Porquinho");
                    int jogoEscolhido;
                    try {
                        jogoEscolhido = Integer.parseInt(scanner.nextLine()); // Lê e converte a escolha do jogo
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Por favor, insira 0 ou 1.");
                        continue;
                    }

                    // Configura o jogo escolhido e inicia o torneio
                    torneio.escolherJogo(jogoEscolhido);
                    torneio.iniciarTorneio(scanner);
                    break;

                case 4:
                    // Exibe o placar atual do torneio
                    torneio.placarTorneio();
                    break;

                case 5:
                    // Grava o estado atual do torneio em um arquivo
                    System.out.print("Digite o nome do arquivo para gravar o torneio: ");
                    String arquivoGravar = scanner.nextLine();
                    torneio.gravarTorneio(arquivoGravar);
                    break;

                case 6:
                    // Lê o estado de um torneio a partir de um arquivo
                    System.out.print("Digite o nome do arquivo para ler o torneio: ");
                    String arquivoLer = scanner.nextLine();
                    Torneio torneioLido = Torneio.lerTorneio(arquivoLer);
                    if (torneioLido != null) {
                        torneio = torneioLido;
                        System.out.println("Torneio lido com sucesso.");
                        torneio.placarTorneio(); // Exibe o placar dos jogadores
                        System.out.println("Jogo escolhido: " + (torneio.getJogoEscolhido() == 0 ? "Jogo de Azar" : "Jogo do Porquinho"));
                    } else {
                        System.out.println("Falha ao ler o torneio.");
                    }
                    break;

                case 7:
                    // Encerra o programa
                    System.out.println("Encerrando o programa.");
                    scanner.close();
                    return;

                default:
                    // Caso a opção não seja válida, exibe uma mensagem de erro
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}
