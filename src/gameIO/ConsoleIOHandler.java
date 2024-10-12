package gameIO;

import java.util.Scanner;

public class ConsoleIOHandler implements IIOHandler{

	private Scanner scanner = new Scanner(System.in);

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public String getInput() {
        return scanner.nextLine();
    }
}
