public class No {
    private Peca peca;
    private No anterior;
    private No proximo;

    public No(Peca peca) {
        this.peca = peca;
        this.anterior = null;
        this.proximo = null;
    }

    public Peca getPeca() {
        return peca;
    }

    public No getAnterior() {
        return anterior;
    }

    public void setAnterior(No anterior) {
        this.anterior = anterior;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }
}