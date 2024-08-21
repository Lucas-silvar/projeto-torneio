import java.util.Random;

public class JogoDados {
    private Random random; // Instância da classe Random para gerar números aleatórios

    // Construtor da classe JogoDados
    public JogoDados() {
        random = new Random(); // Inicializa o gerador de números aleatórios
    }

    // Método para executar o Jogo de Azar
    public boolean jogoAzar(Jogador jogador) {
        int dado1 = random.nextInt(6) + 1; // Gera o valor do primeiro dado (1 a 6)
        int dado2 = random.nextInt(6) + 1; // Gera o valor do segundo dado (1 a 6)
        int soma = dado1 + dado2; // Calcula a soma dos valores dos dados
        jogador.incrementarNumeroDeJogadas(); // Incrementa o número de jogadas do jogador

        // Exibe o resultado do lançamento
        System.out.printf("Lançamento: %d e %d (Soma: %d)%n", dado1, dado2, soma);

        // Verifica se o jogador ganhou ou perdeu com base na soma dos dados
        if (soma == 7 || soma == 11) {
            System.out.println("Jogador ganhou!"); // Ganhou se a soma for 7 ou 11
            return true;
        } else if (soma == 2 || soma == 3 || soma == 12) {
            System.out.println("Jogador perdeu!"); // Perdeu se a soma for 2, 3 ou 12
            return false;
        } else {
            int numeroBuscado = soma; // Armazena a soma como o número a ser buscado
            System.out.println("Número a ser buscado: " + numeroBuscado);

            while (true) {
                dado1 = random.nextInt(6) + 1; // Novo valor para o primeiro dado
                dado2 = random.nextInt(6) + 1; // Novo valor para o segundo dado
                soma = dado1 + dado2; // Nova soma dos dados
                jogador.incrementarNumeroDeJogadas(); // Incrementa o número de jogadas do jogador

                // Exibe o resultado da nova tentativa
                System.out.printf("Nova jogada: %d e %d (Soma: %d)%n", dado1, dado2, soma);

                // Verifica se o jogador ganhou ou perdeu com base na nova soma dos dados
                if (soma == numeroBuscado) {
                    System.out.println("Jogador ganhou!"); // Ganhou se a nova soma for igual ao número buscado
                    return true;
                } else if (soma == 2 || soma == 3 || soma == 12) {
                    System.out.println("Jogador perdeu!"); // Perdeu se a nova soma for 2, 3 ou 12
                    return false;
                }
            }
        }
    }

    // Método para executar o Jogo do Porquinho
    public double jogoPorquinho(Jogador jogador) {
        int pontos = 0; // Inicializa a pontuação do jogador
        int lancamentos = 0; // Inicializa o contador de lançamentos

        // Executa o jogo até que o jogador atinja ou ultrapasse 300 pontos
        while (pontos < 300) {
            lancamentos++; // Incrementa o número de lançamentos
            int dado1 = random.nextInt(6) + 1; // Gera o valor do primeiro dado (1 a 6)
            int dado2 = random.nextInt(6) + 1; // Gera o valor do segundo dado (1 a 6)
            int multiplicacao = dado1 * dado2; // Calcula o produto dos valores dos dados

            // Verifica se os dados mostram o mesmo valor
            if (dado1 == dado2) {
                if (dado1 == 1) {
                    multiplicacao = 30; // Se os dois dados são 1, o valor é 30 pontos
                } else {
                    multiplicacao *= 2; // Se os dados são iguais e diferentes de 1, dobra a multiplicação
                }
            }

            pontos += multiplicacao; // Adiciona a multiplicação à pontuação total
            // Exibe o resultado do lançamento e a pontuação acumulada
            System.out.printf("Lançamento %d: %d * %d = %d (Pontos: %d)%n", lancamentos, dado1, dado2, multiplicacao, pontos);

            // Verifica se o jogador atingiu ou ultrapassou 300 pontos
            if (pontos >= 300) {
                jogador.incrementarNumeroDeJogadas(); // Incrementa o número de jogadas do jogador
                break; // Interrompe o loop quando o objetivo é alcançado
            }
        }

        return pontos; // Retorna a pontuação final do jogador
    }
}
