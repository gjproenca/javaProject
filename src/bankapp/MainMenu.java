package bankapp;

import java.util.Scanner;

public class MainMenu {

    public MainMenu() {
    }

    Scanner input = new Scanner(System.in);
    boolean validOption = false;

    public void open() {
        String option = "-1";

        do {
            if (validOption) {
                System.out.println("Escolha uma opção:");
                options();
            } else {
                System.out.println("\nOpção inserida inválida, escolha uma opção válida:");
                options();
            }

            option = input.nextLine();

            if (!Parse.tryParseInt(option)) {
                validOption = false;
            } else if (Integer.parseInt(option) >= 0 && Integer.parseInt(option) <= 2) {
                validOption = true;
            }
        } while (!validOption);

        switch (Integer.parseInt(option)) {
            case 1:
                break;
            case 2:
                break;
            default:
                System.exit(0);
        }
    }

    private void options() {
        System.out.println("1 - Gerir de contas");
        System.out.println("2 - Efetuar Operações");
        System.out.println("\n0 - Sair");
        System.out.print("\nOpção: ");
    }
    
}
