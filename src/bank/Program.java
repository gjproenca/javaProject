package bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Program {

    public Program() {
        open();
    }

    public static void main(String[] args) {
        new Program();
    }

    private Scanner input = new Scanner(System.in);
    private ArrayList<Account> list = new ArrayList<>();
    private boolean isValid = true;
    private int accountNumber = 1;

    public void open() {
        String option = "-1";

        do {
            if (isValid) {
                System.out.println("\nEscolha uma opção:");
                options();
                isValid = false;
            } else {
                System.out.println("\n" + Color.ANSI_RED + "Opção inserida inválida, escolha uma opção válida:" + Color.ANSI_RESET);
                options();
            }

            option = input.nextLine();

        } while (!Parse.tryParseInt(option) || (Integer.parseInt(option) < 0 || Integer.parseInt(option) > 9));
        isValid = true;

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
        System.out.println("9 - Consultar detalhes");
        System.out.println("\n0 - Sair");
        System.out.print(Color.ANSI_YELLOW + "\nOpção: " + Color.ANSI_RESET);
    }

    private void createAccount(boolean menu, int accountNumber) {
        String custumerName = "";
        String custumerPhoneNumber = "9";
        String option = "-1";
        String nib = "";
        String balance;
        String credit;

        do {
            if (isValid) {
                System.out.print("\nInsira o primeiro e último nome separado por um espaço (ex:John Doe): ");
                isValid = false;
            } else {
                System.out.print(Color.ANSI_RED + "Nome inválido, insira o primeiro e último nome separado por um espaço (ex:John Doe): " + Color.ANSI_RESET);
            }
            custumerName = input.nextLine();
        } while (!Pattern.matches("^[^\\s\\d\\W]{1,}\\s[^\\s\\d\\W]{1,}[^\\s\\d\\W]$", custumerName));
        isValid = true;

        do {
            if (isValid) {
                System.out.print("\nDeseja (1 - Inserir número de telefone) ou (2 - Gerar número de telefone): ");
                isValid = false;
            } else {
                System.out.print(Color.ANSI_RED + "Opção inserida inválida, deseja (1 - Inserir número de telefone) ou (2 - Gerar número de telefone): " + Color.ANSI_RESET);
            }

            option = input.nextLine();

        } while (!Parse.tryParseInt(option) || (Integer.parseInt(option) < 1 || Integer.parseInt(option) > 2));
        isValid = true;

        switch (Integer.parseInt(option)) {
            case 1:
                do {
                    if (isValid) {
                        System.out.print("\nInsira um número de telemóvel: ");
                        custumerPhoneNumber = input.nextLine();
                        isValid = false;
                    } else {
                        System.out.print(Color.ANSI_RED + "Número inválido, insira um número de telemóvel: " + Color.ANSI_RESET);
                        custumerPhoneNumber = input.nextLine();
                    }
                } while (!Pattern.matches("^[9][1236]\\d{7}$", custumerPhoneNumber));
                isValid = true;
                break;
            case 2:
                custumerPhoneNumber += (int) (Math.random() * 3) + 1;
                for (int i = 0; i < 7; i++) {
                    custumerPhoneNumber += (int) (Math.random() * 9) + 0;
                }
                break;
            default:
                System.exit(0);
        }

        do {
            if (isValid) {
                System.out.print("\nDeseja (1 - Inserir NIB) ou (2 - Gerar NIB): ");
                isValid = false;
            } else {
                System.out.print(Color.ANSI_RED + "Opção inserida inválida, deseja (1 - Inserir NIB) ou (2 - Gerar NIB): " + Color.ANSI_RESET);
            }

            option = input.nextLine();

        } while (!Parse.tryParseInt(option) || (Integer.parseInt(option) < 1 || Integer.parseInt(option) > 2));
        isValid = true;

        switch (Integer.parseInt(option)) {
            case 1:
                do {
                    if (isValid) {
                        System.out.print("\nInsira o NIB: ");
                        nib = input.nextLine();
                        isValid = false;
                    } else {
                        System.out.print(Color.ANSI_RED + "NIB inválido, insira um nib com 21 algarismos: " + Color.ANSI_RESET);
                        nib = input.nextLine();
                    }
                } while (!Pattern.matches("^\\d{21}$", nib));
                isValid = true;
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
            if (isValid) {
                System.out.print("\nInsira um montante para o saldo: ");
                balance = input.nextLine();
                isValid = false;
            } else {
                System.out.print(Color.ANSI_RED + "Montante inserido invalido, insira um montante valido: " + Color.ANSI_RESET);
                balance = input.nextLine();
            }
        } while (!Parse.tryParseDouble(balance) || Double.parseDouble(balance) < 0);
        isValid = true;

        do {
            if (isValid) {
                System.out.print("\nInsira um montante para o credito: ");
                credit = input.nextLine();
                isValid = false;
            } else {
                System.out.print(Color.ANSI_RED + "Montante inserido invalido, insira um montante valido: " + Color.ANSI_RESET);
                credit = input.nextLine();
            }
        } while (!Parse.tryParseDouble(credit) || Double.parseDouble(credit) < 0);
        isValid = true;

        list.add(new Account(accountNumber, new Client(custumerName, custumerPhoneNumber), nib, Double.parseDouble(balance), Double.parseDouble(credit)));

        if (menu) {
            this.accountNumber++;
            open();
        }
    }

    private void editAccount(boolean menu) {
        String option = "-1";
        String accountNumberLocal;
        String accountTransactionsCounter = "0";

        if (!list.isEmpty()) {
            optionListAccounts();

            do {
                if (isValid) {
                    System.out.print("\nInsira o número da conta: ");
                    accountNumberLocal = input.nextLine();
                    isValid = false;
                } else {
                    System.out.print(Color.ANSI_RED + "Valor inserido inválido, insira um valor válido: " + Color.ANSI_RESET);
                    accountNumberLocal = input.nextLine();
                }
            } while (!Parse.tryParseInt(accountNumberLocal));
            isValid = true;

            if (accountExists(accountNumberLocal)) {
                accountTransactionsCounter += list.get(accountIndex(accountNumberLocal)).getAccountTransactionsCounter();
                list.remove(accountIndex(accountNumberLocal));
                createAccount(false, Integer.parseInt(accountNumberLocal));
                do {
                    if (isValid) {
                        System.out.print("\nDeseja repor o contador de movimentos (1 - sim/2 - não)? ");
                        isValid = false;
                    } else {
                        System.out.print(Color.ANSI_RED + "Opção inserida inválida, deseja repor o contador de movimentos (1 - sim/2 - não)? " + Color.ANSI_RESET);
                    }

                    option = input.nextLine();

                } while (!Parse.tryParseInt(option) || (Integer.parseInt(option) < 1 || Integer.parseInt(option) > 2));
                isValid = true;

                if (Integer.parseInt(option) == 2) {
                    for (Account i : list) {
                        if (i.getAccountNumber() == Integer.parseInt(accountNumberLocal)) {
                            i.setAccountTransactionsCounter(accountTransactionsCounter);
                            break;
                        }
                    }
                }
            } else if (insertNewAccountNumberOption()) {
                editAccount(false);
            }

            isValid = true;
        } else {
            createAccountEmptyList();
        }

        if (menu) {
            open();
        }
    }

    private boolean insertNewAccountNumberOption() {
        String option = "-1";

        do {
            if (isValid) {
                System.out.print("\nEsta conta não existe, deseja inserir um novo número de conta (1 - sim/2 - não)? ");
                isValid = false;
            } else {
                System.out.print(Color.ANSI_RED + "Opção inserida inválida, deseja inserir um novo número de conta (1 - sim/2 - não)? " + Color.ANSI_RESET);
            }

            option = input.nextLine();
        } while (!Parse.tryParseInt(option) || (Integer.parseInt(option) < 1 || Integer.parseInt(option) > 2));
        isValid = true;

        if (Integer.parseInt(option) == 1) {
            return true;
        }
        return false;
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
        if (!list.isEmpty()) {
            //order list(lambda expression)
            Collections.sort(list, (Account account1, Account account2) -> Integer.toString(account1.getAccountNumber()).compareTo(Integer.toString(account2.getAccountNumber())));

            for (Account i : list) {
                System.out.println("\nNúmero de conta: " + i.getAccountNumber());
                System.out.println("Nome do cliente: " + i.getClientDetails().getCustumerName());
                System.out.println("Número de telefone: " + i.getClientDetails().getCustumerPhoneNumber());
                System.out.println("NIB: " + i.getNib());
                System.out.println("Saldo: " + i.getBalance());
                System.out.println("Plafond de crédito: " + i.getCredit());
                System.out.println("Número de movimentos da conta: " + i.getAccountTransactionsCounter());
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
        System.out.print(Color.ANSI_YELLOW + "\nPrima Enter para continuar..." + Color.ANSI_RESET);
        input.nextLine();
    }

    private void deleteAccount(boolean menu) {
        String accountNumberLocal;

        if (!list.isEmpty()) {
            optionListAccounts();

            do {
                if (isValid) {
                    System.out.print("\nInsira o número da conta: ");
                    accountNumberLocal = input.nextLine();
                    isValid = false;
                } else {
                    System.out.print(Color.ANSI_RED + "Valor inserido inválido, insira um valor válido: " + Color.ANSI_RESET);
                    accountNumberLocal = input.nextLine();
                }
            } while (!Parse.tryParseInt(accountNumberLocal));
            isValid = true;

            if (accountExists(accountNumberLocal)) {
                list.remove(accountIndex(accountNumberLocal));
                System.out.println(Color.ANSI_GREEN + "\nConta apagada com sucesso!" + Color.ANSI_RESET);
                pause();
            } else if (insertNewAccountNumberOption()) {
                deleteAccount(false);
            }
        } else {
            createAccountEmptyList();
        }

        if (menu) {
            open();
        }
    }

    private void createAccountEmptyList() throws NumberFormatException {
        String option = "-1";
        System.out.println("\nNão existem contas!");
        do {
            if (isValid) {
                System.out.print("\nDeseja criar uma conta (1 - sim/2-não)? ");
                isValid = false;
            } else {
                System.out.print(Color.ANSI_RED + "Opção inserida inválida, deseja criar uma conta (1 - sim/2-não)? " + Color.ANSI_RESET);
            }

            option = input.nextLine();

        } while (!Parse.tryParseInt(option) || (Integer.parseInt(option) < 1 || Integer.parseInt(option) > 2));
        isValid = true;

        if (Integer.parseInt(option) == 1) {
            createAccount(false, this.accountNumber);
            this.accountNumber++;
        }
    }

    private void withdraw(boolean menu) {
        String accountNumberLocal;

        if (!list.isEmpty()) {
            optionListAccounts();

            do {
                if (isValid) {
                    System.out.print("\nInsira o número da conta: ");
                    accountNumberLocal = input.nextLine();
                    isValid = false;
                } else {
                    System.out.print(Color.ANSI_RED + "Valor inserido inválido, insira um valor válido: " + Color.ANSI_RESET);
                    accountNumberLocal = input.nextLine();
                }
            } while (!Parse.tryParseInt(accountNumberLocal));
            isValid = true;

            if (accountExists(accountNumberLocal)) {
                list.get(accountIndex(accountNumberLocal)).withdraw();
                pause();
            } else if (insertNewAccountNumberOption()) {
                withdraw(false);
            }
        } else {
            createAccountEmptyList();
        }

        if (menu) {
            open();
        }
    }

    private void deposit(boolean menu) {
        String accountNumberLocal;

        if (!list.isEmpty()) {
            optionListAccounts();

            do {
                if (isValid) {
                    System.out.print("\nInsira o número da conta: ");
                    accountNumberLocal = input.nextLine();
                    isValid = false;
                } else {
                    System.out.print(Color.ANSI_RED + "Valor inserido inválido, insira um valor válido: " + Color.ANSI_RESET);
                    accountNumberLocal = input.nextLine();
                }
            } while (!Parse.tryParseInt(accountNumberLocal));
            isValid = true;

            if (accountExists(accountNumberLocal)) {
                list.get(accountIndex(accountNumberLocal)).deposit();
                pause();
            } else if (insertNewAccountNumberOption()) {
                deposit(false);
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
            if (isValid) {
                System.out.print("\nDeseja listar as contas (1 - sim/2 - não): ");
                isValid = false;
            } else {
                System.out.print(Color.ANSI_RED + "Opção inserida inválida, deseja listar as contas (1 - sim/2 - não): " + Color.ANSI_RESET);
            }

            option = input.nextLine();
        } while (!Parse.tryParseInt(option) || (Integer.parseInt(option) < 1 || Integer.parseInt(option) > 2));
        isValid = true;

        if (Integer.parseInt(option) == 1) {
            listAccounts(false);
        }
    }

    private void checkBalance(boolean menu) {
        String accountNumberLocal;

        if (!list.isEmpty()) {
            optionListAccounts();

            do {
                if (isValid) {
                    System.out.print("\nInsira o número da conta: ");
                    accountNumberLocal = input.nextLine();
                    isValid = false;
                } else {
                    System.out.print(Color.ANSI_RED + "Valor inserido inválido, insira um valor válido: " + Color.ANSI_RESET);
                    accountNumberLocal = input.nextLine();
                }
            } while (!Parse.tryParseInt(accountNumberLocal));
            isValid = true;

            if (accountExists(accountNumberLocal)) {
                for (Account i : list) {
                    if (i.getAccountNumber() == Integer.parseInt(accountNumberLocal)) {
                        System.out.println("\nNome do cliente: " + i.getClientDetails().getCustumerName());
                        System.out.println("NIB: " + i.getNib());
                        System.out.println("Saldo: " + i.getBalance());
                        System.out.println("Plafond de crédito: " + i.getCredit());
                        System.out.println("Saldo total: " + (i.getBalance() + i.getCredit()));
                        break;
                    }
                }
                pause();
            } else if (insertNewAccountNumberOption()) {
                checkBalance(false);
            }
        } else {
            createAccountEmptyList();
        }

        if (menu) {
            open();
        }
    }

    private void checkNumberTransactions(boolean menu) {
        String accountNumberLocal;

        if (!list.isEmpty()) {
            optionListAccounts();

            do {
                if (isValid) {
                    System.out.print("\nInsira o número: ");
                    accountNumberLocal = input.nextLine();
                    isValid = false;
                } else {
                    System.out.print(Color.ANSI_RED + "Valor inserido inválido, insira um valor válido: " + Color.ANSI_RESET);
                    accountNumberLocal = input.nextLine();
                }
            } while (!Parse.tryParseInt(accountNumberLocal));
            isValid = true;

            if (accountExists(accountNumberLocal)) {
                for (Account i : list) {
                    if (i.getAccountNumber() == Integer.parseInt(accountNumberLocal)) {
                        System.out.println("\nNome do cliente: " + i.getClientDetails().getCustumerName());
                        System.out.println("NIB: " + i.getNib());
                        System.out.println("Total de movimentos: " + i.getAccountTransactionsCounter());
                        break;
                    }
                }
                pause();
            } else if (insertNewAccountNumberOption()) {
                checkNumberTransactions(false);
            }
        } else {
            createAccountEmptyList();
        }

        if (menu) {
            open();
        }
    }

    private void checkAccountDetails(boolean menu) {
        String accountNumberLocal;

        if (!list.isEmpty()) {
            optionListAccounts();

            do {
                if (isValid) {
                    System.out.print("\nInsira o número: ");
                    accountNumberLocal = input.nextLine();
                    isValid = false;
                } else {
                    System.out.print(Color.ANSI_RED + "Valor inserido inválido, insira um valor válido: " + Color.ANSI_RESET);
                    accountNumberLocal = input.nextLine();
                }
            } while (!Parse.tryParseInt(accountNumberLocal));
            isValid = true;

            if (accountExists(accountNumberLocal)) {
                for (Account i : list) {
                    if (i.getAccountNumber() == Integer.parseInt(accountNumberLocal)) {
                        System.out.println("\nNúmero de conta: " + i.getAccountNumber());
                        System.out.println("Nome do cliente: " + i.getClientDetails().getCustumerName());
                        System.out.println("Número de telefone: " + i.getClientDetails().getCustumerPhoneNumber());
                        System.out.println("NIB: " + i.getNib());
                        System.out.println("Saldo: " + i.getBalance());
                        System.out.println("Plafond de crédito: " + i.getCredit());
                        System.out.println("Número de movimentos da conta: " + i.getAccountTransactionsCounter());
                        break;
                    }
                }
                pause();
            } else if (insertNewAccountNumberOption()) {
                checkAccountDetails(false);
            }
        } else {
            createAccountEmptyList();
        }

        if (menu) {
            open();
        }
    }

}
