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