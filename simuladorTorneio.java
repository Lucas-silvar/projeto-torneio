import java.util.Scanner;

public class simuladorTorneio {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Torneio torneio = new Torneio(Torneio.MAX_JOGADORES); // Inicializa o torneio com capacidade máxima

        // Testando a sobrecarga do método ajustarSaldo
        Jogador jogador = new Jogador("Jogador1", true);
        jogador.ajustarSaldo(50.0); // Ajusta o saldo usando double
        System.out.println("Após ajustar com double: " + jogador);

        jogador.ajustarSaldo(30);   // Ajusta o saldo usando int (auto-conversão para double)
        System.out.println("Após ajustar com int: " + jogador);

        while (true) {
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

                    Jogador jogadorNovo = new Jogador(id, tipo == 1);
                    torneio.incluirJogador(jogadorNovo);
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

                case 5:
                    System.out.print("Digite o nome do arquivo para gravar o torneio: ");
                    String arquivoGravar = scanner.nextLine();
                    torneio.gravarTorneio(arquivoGravar);
                    break;

                case 6:
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
