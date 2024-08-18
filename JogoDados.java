import java.util.Random;

public class JogoDados {
    private Random random;

    public JogoDados() {
        random = new Random();
    }

    public boolean jogoAzar(Jogador jogador) {
        int dado1 = random.nextInt(6) + 1;
        int dado2 = random.nextInt(6) + 1;
        int soma = dado1 + dado2;

        System.out.printf("Lançamento: %d e %d (Soma: %d)%n", dado1, dado2, soma);

        if (soma == 7 || soma == 11) {
            System.out.println("Jogador ganhou!");
            return true;
        } else if (soma == 2 || soma == 3 || soma == 12) {
            System.out.println("Jogador perdeu!");
            return false;
        } else {
            int numeroBuscado = soma;
            System.out.println("Número a ser buscado: " + numeroBuscado);

            while (true) {
                dado1 = random.nextInt(6) + 1;
                dado2 = random.nextInt(6) + 1;
                soma = dado1 + dado2;
                System.out.printf("Nova jogada: %d e %d (Soma: %d)%n", dado1, dado2, soma);

                if (soma == numeroBuscado) {
                    System.out.println("Jogador ganhou!");
                    return true;
                } else if (soma == 2 || soma == 3 || soma == 12) {
                    System.out.println("Jogador perdeu!");
                    return false;
                }
            }
        }
    }

    public double jogoBozo(Jogador jogador) {
        int[] dados = new int[5];
        int melhorPontuacao = 0;

        for (int tentativa = 0; tentativa < 3; tentativa++) {
            for (int i = 0; i < 5; i++) {
                dados[i] = random.nextInt(6) + 1;
            }

            int pontuacao = calcularPontuacao(dados);
            System.out.printf("Tentativa %d: ", tentativa + 1);
            for (int dado : dados) {
                System.out.print(dado + " ");
            }
            System.out.println("(Pontuação: " + pontuacao + ")");

            if (pontuacao > melhorPontuacao) {
                melhorPontuacao = pontuacao;
            }
        }

        return melhorPontuacao;
    }

    private int calcularPontuacao(int[] dados) {
        int[] contagem = new int[7]; // Contagem das faces dos dados

        for (int dado : dados) {
            contagem[dado]++;
        }

        int pontuacao = 0;

        // Verifica as pontuações de acordo com as regras
        for (int i = 1; i <= 6; i++) {
            if (contagem[i] == 5) {
                pontuacao = 50; // General
                break;
            } else if (contagem[i] == 4) {
                pontuacao = 40; // Quadrada
            } else if (contagem[i] == 3) {
                for (int j = 1; j <= 6; j++) {
                    if (j != i && contagem[j] == 2) {
                        pontuacao = 20; // Fu
                        break;
                    }
                }
                if (pontuacao == 0) {
                    pontuacao = 15; // Terno
                }
            } else if (contagem[i] == 2) {
                pontuacao = Math.max(pontuacao, 10); // Duque
            }
        }

        // Verifica as pontuações especiais
        if (pontuacao == 0) {
            // Verifica se é seguida
            boolean seguida = true;
            for (int i = 0; i < 5; i++) {
                if (i > 0 && dados[i] != dados[i - 1] + 1) {
                    seguida = false;
                    break;
                }
            }
            if (seguida) {
                pontuacao = 30; // Seguida
            }
        }

        return pontuacao;
    }

    public double jogoPorquinho(Jogador jogador) {
        int pontos = 0;
        int lancamentos = 0;

        while (pontos < 300) {
            lancamentos++;
            int dado1 = random.nextInt(6) + 1;
            int dado2 = random.nextInt(6) + 1;
            int multiplicacao = dado1 * dado2;

            if (dado1 == dado2) {
                if (dado1 == 1) {
                    multiplicacao = 30; // Double de 1 é 30 pontos
                } else {
                    multiplicacao *= 2; // Dobro dos valores em outros casos
                }
            }

            pontos += multiplicacao;
            System.out.printf("Lançamento %d: %d * %d = %d (Pontos: %d)%n", lancamentos, dado1, dado2, multiplicacao, pontos);
        }

        return pontos;
    }
}