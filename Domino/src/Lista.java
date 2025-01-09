public class Lista {
    private No inicio;
    private No fim;

    public Lista() {
        this.inicio = null;
        this.fim = null;
    }

    public void adicionarNoInicio(Peca peca) {
        No novo = new No(peca);
        if (inicio == null) {
            inicio = fim = novo;
        } else {
            novo.setProximo(inicio);
            inicio.setAnterior(novo);
            inicio = novo;
        }
    }

    public void adicionarNoFim(Peca peca) {
        No novo = new No(peca);
        if (fim == null) {
            inicio = fim = novo;
        } else {
            novo.setAnterior(fim);
            fim.setProximo(novo);
            fim = novo;
        }
    }

    public void remover(No no) {
        if (no == inicio) {
            inicio = no.getProximo();
            if (inicio != null) {
                inicio.setAnterior(null);
            }
        } else if (no == fim) {
            fim = no.getAnterior();
            if (fim != null) {
                fim.setProximo(null);
            }
        } else {
            No anterior = no.getAnterior();
            No proximo = no.getProximo();
            if (anterior != null) anterior.setProximo(proximo);
            if (proximo != null) proximo.setAnterior(anterior);
        }
    }

    public void exibir() {
        No atual = inicio;
        while (atual != null) {
            System.out.println(atual.getPeca());
            atual = atual.getProximo();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        No atual = inicio;
        while (atual != null) {
            sb.append(atual.getPeca()).append(" ");
            atual = atual.getProximo();
        }
        return sb.toString();
    }

    public No getInicio() {
        return inicio;
    }

    public No getFim() {
        return fim;
    }
}
