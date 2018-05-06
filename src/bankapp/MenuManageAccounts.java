package bankapp;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuManageAccounts {

    public MenuManageAccounts() {
    }

    Scanner input = new Scanner(System.in);
    boolean valid = true;
    ArrayList<Account> list = new ArrayList<>();
    int accountNumber = 1;

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
            } else if (Integer.parseInt(option) >= 0 && Integer.parseInt(option) <= 4) {
                valid = true;
            }
        } while (!valid);

        switch (Integer.parseInt(option)) {
            case 1:
                createAccount();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                listAccounts();
                break;
            default:
                System.exit(0);
        }
    }

    private void options() {
        System.out.println("1 - Criar conta");
        System.out.println("2 - Alterar conta");
        System.out.println("3 - Apagar conta");
        System.out.println("4 - Listar contas");
        System.out.println("\n0 - Sair");
        System.out.print("\nOpção: ");
    }

    private void createAccount() {
        String custumerName;
        String custumerPhoneNumber = "9";
        String option = "-1";
        String nib = "";
        String balance;
        String credit;

        System.out.print("Insira o nome do cliente: ");
        custumerName = input.nextLine();

        do {
            if (valid) {
                System.out.println("Escolha uma opção:");
                System.out.println("1 - Inserir número de telefone");
                System.out.println("2 - Gerar número de telefone");
                System.out.print("\nOpção: ");
                valid = false;
            } else {
                System.out.println("\nOpção inserida inválida, escolha uma opção válida:");
                System.out.println("Escolha uma opção:");
                System.out.println("1 - Inserir número de telefone");
                System.out.println("2 - Gerar número de telefone");
                System.out.print("\nOpção: ");
            }

            option = input.nextLine();

            if (!Parse.tryParseInt(option)) {
                valid = false;
            } else if (Integer.parseInt(option) >= 1 && Integer.parseInt(option) <= 2) {
                valid = true;
            }
        } while (!valid);

        switch (Integer.parseInt(option)) {
            case 1:
                do {
                    if (valid) {
                        System.out.print("Insira um número com 9 algarismos: ");
                        custumerPhoneNumber = input.nextLine();
                        valid = false;
                    } else {
                        System.out.print("Número inválido, insira um número com 9 algarismos: ");
                        custumerPhoneNumber = input.nextLine();
                    }
                } while (!Parse.tryParseInt(custumerPhoneNumber) || custumerPhoneNumber.length() != 9);
                valid = true;
                break;
            case 2:
                for (int i = 0; i < 8; i++) {
                    custumerPhoneNumber += (int) (Math.random() * 9) + 0;
                }
                break;
            default:
                System.exit(0);
        }

        do {
            if (valid) {
                System.out.println("Escolha uma opção:");
                System.out.println("1 - Inserir NIB");
                System.out.println("2 - Gerar NIB");
                System.out.print("\nOpção: ");
                valid = false;
            } else {
                System.out.println("\nOpção inserida inválida, escolha uma opção válida:");
                System.out.println("Escolha uma opção:");
                System.out.println("1 - Inserir NIB");
                System.out.println("2 - Gerar NIB");
                System.out.print("\nOpção: ");
            }

            option = input.nextLine();

            if (!Parse.tryParseInt(option)) {
                valid = false;
            } else if (Integer.parseInt(option) >= 1 && Integer.parseInt(option) <= 2) {
                valid = true;
            }
        } while (!valid);

        switch (Integer.parseInt(option)) {
            case 1:
                do {
                    if (valid) {
                        System.out.print("Insira o NIB: ");
                        nib = input.nextLine();
                        valid = false;
                    } else {
                        System.out.print("NIB inválido, insira um nib com 21 algarismos: ");
                        nib = input.nextLine();
                    }
                } while (!Parse.tryParseDouble(nib) || nib.length() != 21);
                valid = true;
                break;
            case 2:
                for (int i = 0; i < 21; i++) {
                    nib += (int) (Math.random() * 9) + 0;
                }
                break;
            default:
                System.exit(0);
        }

        do {
            if (valid) {
                System.out.print("Insira um montante para o saldo: ");
                balance = input.nextLine();
                valid = false;
            } else {
                System.out.print("Montante inserido invalido, insira um montante valido: ");
                balance = input.nextLine();
            }
        } while (!Parse.tryParseDouble(balance));

        valid = true;

        do {
            if (valid) {
                System.out.print("Insira um montante para o credito: ");
                credit = input.nextLine();
                valid = false;
            } else {
                System.out.print("Montante inserido invalido, insira um montante valido: ");
                credit = input.nextLine();
            }
        } while (!Parse.tryParseDouble(credit));

        valid = true;

        list.add(new Account(accountNumber, custumerName, custumerPhoneNumber, nib, Double.parseDouble(balance), Double.parseDouble(credit)));

        accountNumber++;

        open();
    }

    private void alterAccount() {
        String option = "-1";

        do {
            if (valid) {
                System.out.println("Deseja listar as contas (1 - sim/2 - não): ");
                valid = false;
            } else {
                System.out.println("\nOpção inserida inválida, escolha uma opção válida, prima a tecla 1 - sim ou 2 - não: ");
            }

            option = input.nextLine();

            if (!Parse.tryParseInt(option)) {
                valid = false;
            } else if (Integer.parseInt(option) >= 1 && Integer.parseInt(option) <= 2) {
                valid = true;
            }
        } while (!valid);
    }

    private void listAccounts() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\nNúmero de conta: " + list.get(i).getAccountNumber());
            System.out.println("Nome do cliente: " + list.get(i).getCustumerName());
            System.out.println("Número de telefone: " + list.get(i).getCustumerPhoneNumber());
            System.out.println("NIB: " + list.get(i).getNib());
            System.out.println("Saldo: " + list.get(i).getBalance());
            System.out.println("Plafond de crédito: " + list.get(i).getCredit());
            System.out.println("Número de movimentos da conta: " + list.get(i).getAccoutOperationsCounter() + '\n');
        }

        open();
    }

}
