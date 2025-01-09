import java.util.ArrayList;
import java.util.Collections;

public class Logica {
    private Lista pecasComputador;
    private Lista pecasHumano;
    private Lista pecasJogadas;
    private ArrayList<Peca> monte;

    public Logica() {
        pecasComputador = new Lista();
        pecasHumano = new Lista();
        pecasJogadas = new Lista();
        monte = new ArrayList<>();
        distribuirPecas();
    }

    private void distribuirPecas() {
        ArrayList<Peca> todasPecas = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                todasPecas.add(new Peca(i, j));
            }
        }
        Collections.shuffle(todasPecas);
        for (int i = 0; i < 7; i++) {
            pecasComputador.adicionarNoFim(todasPecas.remove(0));
            pecasHumano.adicionarNoFim(todasPecas.remove(0));
        }
        monte.addAll(todasPecas);
    }

    public void jogarHumano(Input input, Output output) {
        output.exibirMesa(pecasJogadas);
        output.exibirMensagem("Peças disponíveis:");
        pecasHumano.exibir();
        int escolha = input.lerInt("Escolha uma peça (1 a " + contarPecas(pecasHumano) + ") ou 0 para comprar/passar: ");
        if (escolha == 0) {
            if (monte.isEmpty()) {
                output.exibirMensagem("Você passou a vez.");
                return;
            } else {
                Peca novaPeca = monte.remove(0);
                output.exibirMensagem("Você comprou a peça: " + novaPeca);
                pecasHumano.adicionarNoFim(novaPeca);
                return;
            }
        }
        No atual = pecasHumano.getInicio();
        for (int i = 1; i < escolha && atual != null; i++) {
            atual = atual.getProximo();
        }
        if (atual != null && podeJogar(atual.getPeca())) {
            jogarNaMesa(atual.getPeca());
            pecasHumano.remover(atual);
        } else {
            output.exibirMensagem("Peça inválida para jogar. Você deve comprar, passar ou tentar outra peça.");
        }
    }

    public void jogarComputador(Output output) {
        output.exibirMesa(pecasJogadas);
        No atual = pecasComputador.getInicio();
        while (atual != null) {
            if (podeJogar(atual.getPeca())) {
                jogarNaMesa(atual.getPeca());
                pecasComputador.remover(atual);
                output.exibirMensagem("Computador jogou: " + atual.getPeca());
                return;
            }
            atual = atual.getProximo();
        }
        if (!monte.isEmpty()) {
            Peca novaPeca = monte.remove(0);
            pecasComputador.adicionarNoFim(novaPeca);
            output.exibirMensagem("Computador comprou uma peça.");
        } else {
            output.exibirMensagem("Computador passou a vez.");
        }
    }

    private void jogarNaMesa(Peca peca) {
        if (pecasJogadas.getInicio() == null) {
            pecasJogadas.adicionarNoFim(peca);
        } else {
            int esquerda = pecasJogadas.getInicio().getPeca().getNumero1();
            int direita = pecasJogadas.getFim().getPeca().getNumero2();
            if (peca.getNumero2() == esquerda) {
                pecasJogadas.adicionarNoInicio(peca);
            } else if (peca.getNumero1() == esquerda) {
                pecasJogadas.adicionarNoInicio(peca.inverter());
            } else if (peca.getNumero1() == direita) {
                pecasJogadas.adicionarNoFim(peca);
            } else if (peca.getNumero2() == direita) {
                pecasJogadas.adicionarNoFim(peca.inverter());
            }
        }
    }

    private boolean podeJogar(Peca peca) {
        if (pecasJogadas.getInicio() == null) return true;
        int esquerda = pecasJogadas.getInicio().getPeca().getNumero1();
        int direita = pecasJogadas.getFim().getPeca().getNumero2();
        return peca.podeConectar(esquerda) || peca.podeConectar(direita);
    }

    public boolean verificarFimDeJogo() {
        return contarPecas(pecasComputador) == 0 || contarPecas(pecasHumano) == 0;
    }

    public String determinarVencedor() {
        int pecasComp = contarPecas(pecasComputador);
        int pecasHum = contarPecas(pecasHumano);
        if (pecasComp < pecasHum) return "Computador";
        else if (pecasHum < pecasComp) return "Humano";
        else return "Empate";
    }

    private int contarPecas(Lista lista) {
        int contador = 0;
        No atual = lista.getInicio();
        while (atual != null) {
            contador++;
            atual = atual.getProximo();
        }
        return contador;
    }
}
