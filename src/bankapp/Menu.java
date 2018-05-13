package bankapp;

import java.util.ArrayList;
import java.util.Scanner;

//TODO: move code to main
//TODO: add set variable to 0 if its false
//FIX: modify account reset number of account movements
//TODO: extract 'esta conta nao existe' to its own method
public class Menu {

    private Scanner input = new Scanner(System.in);
    private boolean valid = true;
    private ArrayList<Account> list = new ArrayList<>();
    private int accountNumber = 1;

    public Menu() {
    }

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
                editAccount(true);
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
            case 6:
                deposit(true);
                break;
            case 7:
                checkBalance(true);
                break;
            case 8:
                checkNumberTransactions(true);
                break;
            case 9:
                checkAccountDetails(true);
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
        System.out.println("\n7 - Consultar saldo");
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
                System.out.print("\nDeseja (1 - Inserir número de telefone) ou (2 - Gerar número de telefone): ");
                valid = false;
            } else {
                System.out.print("\nOpção inserida inválida, deseja (1 - Inserir número de telefone) ou (2 - Gerar número de telefone): ");
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
                System.out.print("\nDeseja (1 - Inserir NIB) ou (2 - Gerar NIB): ");
                valid = false;
            } else {
                System.out.print("\nOpção inserida inválida, deseja (1 - Inserir NIB) ou (2 - Gerar NIB): ");
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

        list.add(new Account(accountNumber, new Client(custumerName, custumerPhoneNumber), nib, Double.parseDouble(balance), Double.parseDouble(credit)));

        if (menu) {
            this.accountNumber++;
            open();
        }
    }

    private void editAccount(boolean menu) {
        String option = "-1";
        String accountNumberLocal;

        if (!list.isEmpty()) {
            optionListAccounts();

            do {
                if (valid) {
                    System.out.print("\nInsira o número da conta: ");
                    accountNumberLocal = input.nextLine();
                } else {
                    System.out.print("Valor inserido inválido, insira um valor válido: ");
                    accountNumberLocal = input.nextLine();
                }
            } while (!Parse.tryParseInt(accountNumberLocal));

            if (accountExists(accountNumberLocal)) {
                list.remove(accountIndex(accountNumberLocal));
                createAccount(false, Integer.parseInt(accountNumberLocal));
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
                    editAccount(false);
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
            for (Account i : list) {
                System.out.println("\nNúmero de conta: " + i.getAccountNumber());
                System.out.println("Nome do cliente: " + i.getClientDetails().getCustumerName());
                System.out.println("Número de telefone: " + i.getClientDetails().getCustumerPhoneNumber());
                System.out.println("NIB: " + i.getNib());
                System.out.println("Saldo: " + i.getBalance());
                System.out.println("Plafond de crédito: " + i.getCredit());
                System.out.println("Número de movimentos da conta: " + i.getAccoutTransactionsCounter());
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
        String accountNumberLocal;

        if (!list.isEmpty()) {
            optionListAccounts();

            do {
                if (valid) {
                    System.out.print("\nInsira o número da conta: ");
                    accountNumberLocal = input.nextLine();
                } else {
                    System.out.print("Valor inserido inválido, insira um valor válido: ");
                    accountNumberLocal = input.nextLine();
                }
            } while (!Parse.tryParseInt(accountNumberLocal));

            if (accountExists(accountNumberLocal)) {
                list.remove(accountIndex(accountNumberLocal));
                System.out.println("\nConta apagada com sucesso!");
                pause();
            } else {
                do {
                    if (valid) {
                        System.out.print("\nEsta conta não existe, deseja inserir um novo número de conta (1 - sim/2-não)? ");
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
        String accountNumberLocal;

        if (!list.isEmpty()) {
            optionListAccounts();

            do {
                if (valid) {
                    System.out.print("\nInsira o número da conta: ");
                    accountNumberLocal = input.nextLine();
                } else {
                    System.out.print("Valor inserido inválido, insira um valor válido: ");
                    accountNumberLocal = input.nextLine();
                }
            } while (!Parse.tryParseInt(accountNumberLocal));

            if (accountExists(accountNumberLocal)) {
                list.get(accountIndex(accountNumberLocal)).withdraw();
                pause();
            } else {
                do {
                    if (valid) {
                        System.out.print("\nEsta conta não existe, deseja inserir um novo número de conta (1 - sim/2-não)? ");
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

    private void deposit(boolean menu) {
        String option = "-1";
        String accountNumberLocal;

        if (!list.isEmpty()) {
            optionListAccounts();

            do {
                if (valid) {
                    System.out.print("\nInsira o número da conta: ");
                    accountNumberLocal = input.nextLine();
                } else {
                    System.out.print("Valor inserido inválido, insira um valor válido: ");
                    accountNumberLocal = input.nextLine();
                }
            } while (!Parse.tryParseInt(accountNumberLocal));

            if (accountExists(accountNumberLocal)) {
                list.get(accountIndex(accountNumberLocal)).deposit();
                pause();
            } else {
                do {
                    if (valid) {
                        System.out.print("\nEsta conta não existe, deseja inserir um novo número de conta (1 - sim/2-não)? ");
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
                    deposit(false);
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

    private void checkBalance(boolean menu) {
        String option = "-1";
        String accountNumberLocal;

        if (!list.isEmpty()) {
            optionListAccounts();

            do {
                if (valid) {
                    System.out.print("\nInsira o número da conta: ");
                    accountNumberLocal = input.nextLine();
                } else {
                    System.out.print("Valor inserido inválido, insira um valor válido: ");
                    accountNumberLocal = input.nextLine();
                }
            } while (!Parse.tryParseInt(accountNumberLocal));

            if (accountExists(accountNumberLocal)) {
                for (Account i : list) {
                    if (i.getAccountNumber() == Integer.parseInt(accountNumberLocal)) {
                        System.out.println("\nNIB: " + i.getNib());
                        System.out.println("Saldo: " + i.getBalance());
                        System.out.println("Plafond de crédito: " + i.getCredit());
                        System.out.println("Saldo total: " + (i.getBalance() + i.getCredit()));
                        break;
                    }
                }
                pause();
            } else {
                do {
                    if (valid) {
                        System.out.print("\nEsta conta não existe, deseja inserir um novo número de conta (1 - sim/2-não)? ");
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
                    checkBalance(false);
                }
            }
        } else {
            createAccountEmptyList();
        }

        if (menu) {
            open();
        }
    }

    private void checkNumberTransactions(boolean menu) {
        String option = "-1";
        String accountNumberLocal;

        if (!list.isEmpty()) {
            optionListAccounts();

            do {
                if (valid) {
                    System.out.print("\nInsira o número: ");
                    accountNumberLocal = input.nextLine();
                } else {
                    System.out.print("Valor inserido inválido, insira um valor válido: ");
                    accountNumberLocal = input.nextLine();
                }
            } while (!Parse.tryParseInt(accountNumberLocal));

            if (accountExists(accountNumberLocal)) {
                for (Account i : list) {
                    if (i.getAccountNumber() == Integer.parseInt(accountNumberLocal)) {
                        System.out.println("\nNIB: " + i.getNib());
                        System.out.println("Total de movimentos: " + i.getAccoutTransactionsCounter());
                        break;
                    }
                }
                pause();
            } else {
                do {
                    if (valid) {
                        System.out.print("\nEsta conta não existe, deseja inserir um novo número de conta (1 - sim/2-não)? ");
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
                    checkNumberTransactions(false);
                }
            }
        } else {
            createAccountEmptyList();
        }

        if (menu) {
            open();
        }
    }

    private void checkAccountDetails(boolean menu) {
        String option = "-1";
        String accountNumberLocal;

        if (!list.isEmpty()) {
            optionListAccounts();

            do {
                if (valid) {
                    System.out.print("\nInsira o número: ");
                    accountNumberLocal = input.nextLine();
                } else {
                    System.out.print("Valor inserido inválido, insira um valor válido: ");
                    accountNumberLocal = input.nextLine();
                }
            } while (!Parse.tryParseInt(accountNumberLocal));

            if (accountExists(accountNumberLocal)) {
                for (Account i : list) {
                    if (i.getAccountNumber() == Integer.parseInt(accountNumberLocal)) {
                        System.out.println("\nNúmero de conta: " + i.getAccountNumber());
                        System.out.println("Nome do cliente: " + i.getClientDetails().getCustumerName());
                        System.out.println("Número de telefone: " + i.getClientDetails().getCustumerPhoneNumber());
                        System.out.println("NIB: " + i.getNib());
                        System.out.println("Saldo: " + i.getBalance());
                        System.out.println("Plafond de crédito: " + i.getCredit());
                        System.out.println("Número de movimentos da conta: " + i.getAccoutTransactionsCounter());
                        break;
                    }
                }
                pause();
            } else {
                do {
                    if (valid) {
                        System.out.print("\nEsta conta não existe, deseja inserir um novo número de conta (1 - sim/2-não)? ");
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
                    checkAccountDetails(false);
                }
            }
        } else {
            createAccountEmptyList();
        }

        if (menu) {
            open();
        }
    }

}
