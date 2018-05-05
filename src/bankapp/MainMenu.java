package bankapp;

import java.util.Scanner;

public class MainMenu {

    public MainMenu() {
    }

    Scanner input = new Scanner(System.in);
    MenuManageAccounts menuManageAccounts = new MenuManageAccounts();
    boolean valid = true;

    public void open() {
        String option = "-1";

        do {
            if (valid) {
                System.out.println("Escolha uma opção:");
                options();
                valid = false;
            } else {
                System.out.println("\nOpção inserida inválida, escolha uma opção válida:");
                options();
            }

            option = input.nextLine();

            if (!Parse.tryParseInt(option)) {
                valid = false;
            } else if (Integer.parseInt(option) >= 0 && Integer.parseInt(option) <= 2) {
                valid = true;
            }
        } while (!valid);

        switch (Integer.parseInt(option)) {
            case 1:
                menuManageAccounts.open();
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
