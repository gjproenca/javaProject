package bankapp;

import java.util.Scanner;

public class Menu {

    public Menu() {
    }

    Scanner input = new Scanner(System.in);

    public void open() {
        String option = "-1";
        boolean validOption = true;

        do {
            if (validOption) {
                System.out.println("Escolha uma opção:");
                options();
            } else {
                System.out.println("\nOpção inserida inválida, escolha uma opção válida:");
                options();
            }

            option = input.nextLine();

            validOption = false;
        } while ((!Parse.tryParseInt(option))
                || (Integer.parseInt(option) < 0 && Integer.parseInt(option) > 2)
                || (Integer.parseInt(option) != 0));
    }

    private void options() {
        System.out.println("1 - Gerir de contas");
        System.out.println("2 - Efetuar Operações");
        System.out.println("\n0 - Sair");
        System.out.print("\nOpção: ");
    }
}
