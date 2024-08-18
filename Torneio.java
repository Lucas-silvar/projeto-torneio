import java.util.ArrayList;
import java.util.InputMismatchException; // Import necessário
import java.util.List;
import java.util.Scanner;

public class Torneio {
    private List<Jogador> jogadores;
    private int jogoEscolhido;

    public Torneio() {
        jogadores = new ArrayList<>();
        jogoEscolhido = -1;
    }

    public void incluirJogador(Jogador jogador) {
        jogadores.add(jogador);
        System.out.println("Jogador " + jogador.getId() + " foi incluído.");
    }

    public void removerJogador(String id) {
        jogadores.removeIf(jogador -> jogador.getId().equals(id));
        System.out.println("Jogador " + id + " foi removido.");
    }

    public void escolherJogo(int jogo) {
        this.jogoEscolhido = jogo;
        System.out.println("Jogo escolhido para o torneio: " + jogoEscolhido);
    }

    public void iniciarTorneio(Scanner scanner) {
        if (jogadores.size() < 2) {
            System.out.println("É necessário pelo menos 2 jogadores para iniciar o torneio.");
            return;
        }

        aplicarRegraJogo(scanner);
        System.out.println("Torneio finalizado.");
    }

    private void aplicarRegraJogo(Scanner scanner) {
        JogoDados jogoDados = new JogoDados();

        for (Jogador jogador : jogadores) {
            System.out.printf("Jogador %s (%s) está jogando.%n", jogador.getId(), jogador.getTipo());
            System.out.printf("Saldo atual: %.2f%n", jogador.getSaldo());

            double aposta = 0;
            while (true) {
                System.out.print("Informe o valor que deseja apostar: ");
                try {
                    aposta = scanner.nextDouble();
                    scanner.nextLine();
                    if (aposta > 0 && aposta <= jogador.getSaldo()) {
                        break;
                    } else {
                        System.out.println("Valor de aposta inválido. Deve ser positivo e não maior que o saldo.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, insira um número.");
                    scanner.nextLine();
                }
            }

            jogador.setAposta(aposta);

            boolean ganhou = false;
            double premio = 0;

            switch (jogoEscolhido) {
                case 0:
                    ganhou = jogoDados.jogoAzar(jogador);
                    break;
                case 1:
                    premio = jogoDados.jogoBozo(jogador);
                    break;
                case 2:
                    premio = jogoDados.jogoPorquinho(jogador);
                    break;
            }

            if (ganhou || premio > 0) {
                double valorGanho = premio > 0 ? premio : aposta;
                jogador.ajustarSaldo(valorGanho);
                System.out.printf("Jogador %s ganhou! Novo saldo: %.2f%n", jogador.getId(), jogador.getSaldo());
            } else {
                jogador.ajustarSaldo(-aposta);
                System.out.printf("Jogador %s perdeu. Novo saldo: %.2f%n", jogador.getId(), jogador.getSaldo());
            }
        }
    }

    public void placarTorneio() {
        System.out.println("Placar do Torneio:");
        for (Jogador jogador : jogadores) {
            System.out.printf("Jogador: %s, Tipo: %s, Saldo: %.2f%n", jogador.getId(), jogador.getTipo(), jogador.getSaldo());
        }
    }
}
