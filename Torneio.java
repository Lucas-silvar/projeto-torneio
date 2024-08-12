import java.util.ArrayList;
import java.util.List;

public class Torneio {
    private List<Jogador> jogadores;

    public Torneio() {
        this.jogadores = new ArrayList<>();
    }

    public void adicionarJogador(Jogador jogador) {
        if (jogadores.size() < 10) {
            jogadores.add(jogador);
        }
    }

    public void removerJogador(String id) {
        jogadores.removeIf(j -> j.getId().equals(id));
    }

    public void iniciarTorneio() {
        if (jogadores.size() < 2) {
            System.out.println("Número insuficiente de jogadores.");
            return;
        }

        Dado[] dados = {new Dado(), new Dado()};
        for (Jogador jogador : jogadores) {
            jogador.setAposta(jogador.getSaldo() / 5);
            int resultado = jogador.jogar(dados);
            System.out.println("Jogador: " + jogador.getId());
            System.out.println("Aposta: " + jogador.getAposta());
            System.out.println("Resultado: " + dados[0].getValor() + " " + dados[1].getValor());
            System.out.println("Resultado do Jogo: " + resultado);
            if (resultado > 0) {
                jogador.atualizarSaldo(jogador.getAposta());
            } else {
                jogador.atualizarSaldo(-jogador.getAposta());
            }
            System.out.println("Saldo: " + jogador.getSaldo());
        }

        // Determinar vencedor
        Jogador vencedor = determinarVencedor();
        if (vencedor != null) {
            System.out.println("Vencedor: " + vencedor.getId());
        }
    }

    private Jogador determinarVencedor() {
        // Implementar lógica para determinar o vencedor
        // Placeholder para o exemplo
        return jogadores.stream()
                .max((j1, j2) -> Integer.compare(j1.getSaldo(), j2.getSaldo()))
                .orElse(null);
    }
}
