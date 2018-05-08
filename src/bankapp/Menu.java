package bankapp;

import java.util.ArrayList;
import java.util.Scanner;

//TODO: move code to main
//FIX: check, if its fixed, (able to input negative values)
//TODO: check if account movement increments
public class Menu {

    public Menu() {
    }

    private final Scanner input = new Scanner(System.in);
    private boolean valid = true;
    private ArrayList<Account> list = new ArrayList<>();
    private int accountNumber = 1;

    public void open() {
        String option = "-1";

        do {
            if (valid) {
                System.out.println("\nEscolha uma opção:");
                options();
                valid = false;
            } else {
                System.out.println("Opção inserida inválida, escolha uma opção válida:");
                options();
            }

            option = input.nextLine();

            //TODO: extract option into method with range
            if (!Parse.tryParseInt(option)) {
                valid = false;
            } else if (Integer.parseInt(option) >= 0 && Integer.parseInt(option) <= 9) {
                valid = true;
            }
        } while (!valid);

        switch (Integer.parseInt(option)) {
            case 1:
                createAccount(true, this.accountNumber);
                break;
            case 2:
                alterAccount(true);
                break;
            case 3:
                deleteAccount(true);
                break;
            case 4:
                listAccounts(true);
                break;
            case 5:
                withdraw(true);
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
        System.out.println("\n5 - Levantar dinheiro");
        System.out.println("6 - Depositar dinheiro");
        System.out.println("7 - Consultar saldo");
        System.out.println("8 - Consultar número de movimentos");
        System.out.println("9 - Consultar detalhes da conta");
        System.out.println("\n0 - Sair");
        System.out.print("\nOpção: ");
    }

    private void createAccount(boolean menu, int accountNumber) {
        String custumerName;
        String custumerPhoneNumber = "9";
        String option = "-1";
        String nib = "";
        String balance;
        String credit;

        System.out.print("\nInsira o nome do cliente: ");
        custumerName = input.nextLine();

        do {
            if (valid) {
                System.out.println("\nEscolha uma opção:");
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
                        System.out.print("\nInsira um número com 9 algarismos: ");
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
                //TODO: join option into one line
                System.out.println("\nEscolha uma opção:");
                System.out.println("1 - Inserir NIB");
                System.out.println("2 - Gerar NIB");
                System.out.print("\nOpção: ");
                valid = false;
            } else {
                //TODO: join option into one line
                System.out.println("\nOpção inserida inválida, escolha uma opção válida:");
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
                        System.out.print("\nInsira o NIB: ");
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
            do {
                if (valid) {
                    System.out.print("\nInsira um montante para o saldo: ");
                    balance = input.nextLine();
                    valid = false;
                } else {
                    System.out.print("Montante inserido invalido, insira um montante valido: ");
                    balance = input.nextLine();
                }
            } while (!Parse.tryParseDouble(balance));
        } while (Double.parseDouble(balance) < 0);
        valid = true;

        do {
            do {
                if (valid) {
                    System.out.print("\nInsira um montante para o credito: ");
                    credit = input.nextLine();
                    valid = false;
                } else {
                    System.out.print("Montante inserido invalido, insira um montante valido: ");
                    credit = input.nextLine();
                }
            } while (!Parse.tryParseDouble(credit));
        } while (Double.parseDouble(credit) < 0);
        valid = true;

        list.add(new Account(accountNumber, custumerName, custumerPhoneNumber, nib, Double.parseDouble(balance), Double.parseDouble(credit)));

        if (menu) {
            this.accountNumber++;
            open();
        }
    }

    private void alterAccount(boolean menu) {
        String option = "-1";
        String accountNumber;

        if (!list.isEmpty()) {
            optionListAccounts();

            do {
                if (valid) {
                    System.out.print("\nInsira o número da conta a alterar: ");
                    accountNumber = input.nextLine();
                } else {
                    System.out.print("Valor inserido inválido, insira um valor válido: ");
                    accountNumber = input.nextLine();
                }
            } while (!Parse.tryParseInt(accountNumber));

            if (accountExists(accountNumber)) {
                list.remove(accountIndex(accountNumber));
                createAccount(false, Integer.parseInt(accountNumber));
            } else {
                do {
                    if (valid) {
                        System.out.print("\nEsta conta não existe, deseja inserir um novo número de conta (1 - sim/2 - não)? ");
                        valid = false;
                    } else {
                        System.out.print("Opção inserida inválida, deseja inserir um novo número de conta (1 - sim/2 - não)? ");
                    }

                    option = input.nextLine();

                    if (!Parse.tryParseInt(option)) {
                        valid = false;
                    } else if (Integer.parseInt(option) >= 1 && Integer.parseInt(option) <= 2) {
                        valid = true;
                    }
                } while (!valid);

                if (Integer.parseInt(option) == 1) {
                    alterAccount(false);
                }
            }

            valid = true;
        } else {
            createAccountEmptyList();
        }

        if (menu) {
            open();
        }
    }

    private int accountIndex(String accountNumber) throws NumberFormatException {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAccountNumber() == Integer.parseInt(accountNumber)) {
                return i;
            }
        }
        return -1;
    }

    private boolean accountExists(String accountNumber) throws NumberFormatException {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAccountNumber() == Integer.parseInt(accountNumber)) {
                return true;
            }
        }
        return false;
    }

    private void listAccounts(boolean menu) {
        String option = "-1";

        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println("\nNúmero de conta: " + list.get(i).getAccountNumber());
                System.out.println("Nome do cliente: " + list.get(i).getCustumerName());
                System.out.println("Número de telefone: " + list.get(i).getCustumerPhoneNumber());
                System.out.println("NIB: " + list.get(i).getNib());
                System.out.println("Saldo: " + list.get(i).getBalance());
                System.out.println("Plafond de crédito: " + list.get(i).getCredit());
                System.out.println("Número de movimentos da conta: " + list.get(i).getAccoutOperationsCounter());
            }
            pause();
        } else {
            createAccountEmptyList();
        }

        if (menu) {
            open();
        }
    }

    private void pause() {
        System.out.print("\nPrima Enter para continuar...");
        input.nextLine();
    }

    private void deleteAccount(boolean menu) {
        String option = "-1";
        String accountNumber;

        if (!list.isEmpty()) {
            optionListAccounts();

            do {
                if (valid) {
                    System.out.print("\nInsira o número da conta a apagar: ");
                    accountNumber = input.nextLine();
                } else {
                    System.out.print("Valor inserido inválido, insira um valor válido: ");
                    accountNumber = input.nextLine();
                }
            } while (!Parse.tryParseInt(accountNumber));

            if (accountExists(accountNumber)) {
                list.remove(accountIndex(accountNumber));
                System.out.println("\nConta apagada com sucesso!");
                pause();
            } else {
                do {
                    if (valid) {
                        System.out.println("\nEsta conta não existe, deseja inserir um novo número de conta (1 - sim/2-não)? ");
                        valid = false;
                    } else {
                        System.out.print("Opção inserida inválida, deseja inserir um novo número de conta (1 - sim/2-não)? ");
                    }

                    option = input.nextLine();

                    if (!Parse.tryParseInt(option)) {
                        valid = false;
                    } else if (Integer.parseInt(option) >= 1 && Integer.parseInt(option) <= 2) {
                        valid = true;
                    }
                } while (!valid);

                if (Integer.parseInt(option) == 1) {
                    deleteAccount(false);
                }
            }
        } else {
            createAccountEmptyList();
        }

        if (menu) {
            open();
        }
    }

    private void createAccountEmptyList() throws NumberFormatException {
        String option;
        System.out.println("\nNão existem contas!");
        do {
            if (valid) {
                System.out.print("\nDeseja criar uma conta (1 - sim/2-não)? ");
                valid = false;
            } else {
                System.out.print("Opção inserida inválida, Deseja criar uma conta (1 - sim/2-não)? ");
            }

            option = input.nextLine();

            if (!Parse.tryParseInt(option)) {
                valid = false;
            } else if (Integer.parseInt(option) >= 1 && Integer.parseInt(option) <= 2) {
                valid = true;
            }
        } while (!valid);
        if (Integer.parseInt(option) == 1) {
            createAccount(false, this.accountNumber);
            this.accountNumber++;
        }
    }

    private void withdraw(boolean menu) {
        String option = "-1";
        String accountNumber;

        if (!list.isEmpty()) {
            optionListAccounts();

            do {
                if (valid) {
                    System.out.print("\nInsira o número da conta a levantar dinheiro: ");
                    accountNumber = input.nextLine();
                } else {
                    System.out.print("Valor inserido inválido, insira um valor válido: ");
                    accountNumber = input.nextLine();
                }
            } while (!Parse.tryParseInt(accountNumber));

            if (accountExists(accountNumber)) {
                list.get(accountIndex(accountNumber)).withdraw();
                pause();
            } else {
                do {
                    if (valid) {
                        System.out.println("\nEsta conta não existe, deseja inserir um novo número de conta (1 - sim/2-não)? ");
                        valid = false;
                    } else {
                        System.out.print("Opção inserida inválida, deseja inserir um novo número de conta (1 - sim/2-não)? ");
                    }

                    option = input.nextLine();

                    if (!Parse.tryParseInt(option)) {
                        valid = false;
                    } else if (Integer.parseInt(option) >= 1 && Integer.parseInt(option) <= 2) {
                        valid = true;
                    }
                } while (!valid);

                if (Integer.parseInt(option) == 1) {
                    withdraw(false);
                }
            }
        } else {
            createAccountEmptyList();
        }

        if (menu) {
            open();
        }
    }

    private void optionListAccounts() throws NumberFormatException {
        String option;
        do {
            if (valid) {
                System.out.print("\nDeseja listar as contas (1 - sim/2 - não): ");
                valid = false;
            } else {
                System.out.print("Opção inserida inválida, escolha uma opção válida, prima a tecla 1 - sim ou 2 - não: ");
            }

            option = input.nextLine();

            if (!Parse.tryParseInt(option)) {
                valid = false;
            } else if (Integer.parseInt(option) >= 1 && Integer.parseInt(option) <= 2) {
                valid = true;
            }
        } while (!valid);
        if (Integer.parseInt(option) == 1) {
            listAccounts(false);
        }
    }

}
