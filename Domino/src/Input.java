import java.util.Scanner;

public class Input {
    private Scanner scanner;

    public Input() {
        scanner = new Scanner(System.in);
    }

    public int lerInt(String mensagem) {
        System.out.println(mensagem);
        return scanner.nextInt();
    }
}
