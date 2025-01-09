public class Menu {
    private Input input;
    private Output output;
    private Logica logica;

    public Menu() {
        input = new Input();
        output = new Output();
        logica = new Logica();
    }

    public void iniciar() {
        output.exibirMensagem("Bem-vindo ao Jogo de Dominó!");
        while (!logica.verificarFimDeJogo()) {
            output.exibirMensagem("Sua vez:");
            logica.jogarHumano(input, output);
            if (!logica.verificarFimDeJogo()) {
                output.exibirMensagem("Vez do computador:");
                logica.jogarComputador(output);
            }
        }
        output.exibirMensagem("Jogo encerrado! O vencedor é: " + logica.determinarVencedor());
    }
}
