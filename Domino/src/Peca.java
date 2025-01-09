public class Peca {
    private int numero1;
    private int numero2;

    public Peca(int numero1, int numero2) {
        this.numero1 = numero1;
        this.numero2 = numero2;
    }

    public int getNumero1() {
        return numero1;
    }

    public int getNumero2() {
        return numero2;
    }

    public boolean podeConectar(int numero) {
        return numero1 == numero || numero2 == numero;
    }

    public Peca inverter() {
        return new Peca(numero2, numero1);
    }

    @Override
    public String toString() {
        return "[" + numero1 + "|" + numero2 + "]";
    }
}