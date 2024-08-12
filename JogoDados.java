public class JogoDados {

    public static class JogoAzar {
        public static int jogar(Dado[] dados) {
            int soma = dados[0].getValor() + dados[1].getValor();
            if (soma == 7 || soma == 11) {
                return 1; // Vitória
            } else if (soma == 2 || soma == 3 || soma == 12) {
                return -1; // Derrota
            } else {
                // Continuar jogando até encontrar a soma do primeiro lançamento
                int numeroBuscado = soma;
                while (true) {
                    dados[0].lançar();
                    dados[1].lançar();
                    soma = dados[0].getValor() + dados[1].getValor();
                    if (soma == numeroBuscado) {
                        return 1; // Vitória
                    } else if (soma == 7) {
                        return -1; // Derrota
                    }
                }
            }
        }
    }

    public static class Bozo {
        public static int jogar(Dado[] dados) {
            int maxPontuacao = 0;
            for (int i = 0; i < 3; i++) {
                for (Dado dado : dados) {
                    dado.lançar();
                }
                int pontuacao = calcularPontuacao(dados);
                if (pontuacao > maxPontuacao) {
                    maxPontuacao = pontuacao;
                }
            }
            return maxPontuacao;
        }

        private static int calcularPontuacao(Dado[] dados) {
            // Implementar a lógica de cálculo de pontuação de acordo com as regras do Bozo
            // Aqui, deve-se implementar as regras para calcular a pontuação
            return 0; // Placeholder
        }
    }

    public static class JogoPorquinho {
        public static int jogar(Dado[] dados) {
            int pontos = 0;
            while (pontos < 300) {
                dados[0].lançar();
                dados[1].lançar();
                int multiplicacao = dados[0].getValor() * dados[1].getValor();
                if (dados[0].getValor() == dados[1].getValor() && dados[0].getValor() == 1) {
                    pontos += 30;
                } else {
                    pontos += multiplicacao * (dados[0].getValor() == dados[1].getValor() ? 2 : 1);
                }
                if (pontos >= 300) {
                    break;
                }
            }
            return pontos;
        }
    }
}
