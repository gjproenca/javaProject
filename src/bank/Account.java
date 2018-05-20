package bank;

import java.util.Scanner;

public class Account {

    //constructor
    public Account(int accountNumber, Client clientDetails, String nib, double balance, double credit) {
        this.accountNumber = accountNumber;
        this.clientDetails = clientDetails;
        this.nib = nib;
        this.balance = balance;
        this.credit = credit;
    }

    public Account() {
    }

    //atributes
    private final Scanner input = new Scanner(System.in);
    private int accountNumber = 0;
    private Client clientDetails;
    private String nib;
    private double balance = 0;
    private double credit = 0;
    private int accountTransactionsCounter = 0;

    //getters and setters
    public int getAccountNumber() {
        return accountNumber;
    }

    public Client getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(Client clientDetails) {
        this.clientDetails = clientDetails;
    }

    public String getNib() {
        return nib;
    }

    public void setNib(String nib) {
        this.nib = nib;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public int getAccountTransactionsCounter() {
        return accountTransactionsCounter;
    }

    public void setAccountTransactionsCounter(String accountTransactionsCounter) {
        this.accountTransactionsCounter = Integer.parseInt(accountTransactionsCounter);
    }

    //methods
    public void deposit() {
        String amount = "0";
        boolean isValid = true;

        do {
            if (isValid) {
                System.out.print("\nInsira o montante a depositar: ");
                amount = input.nextLine();
                isValid = false;
            } else {
                System.out.print(Color.ANSI_RED + "Montante inserido invalido, insira um montante valido: " + Color.ANSI_RESET);
                amount = input.nextLine();
            }
        } while (!Parse.tryParseDouble(amount));

        if (Double.parseDouble(amount) < 0) {
            System.out.println(Color.ANSI_RED + "\nNão foi possível realizar a operação, montante inválido!" + Color.ANSI_RESET);
        } else if (Double.isInfinite(Double.parseDouble(amount))) {
            System.out.println(Color.ANSI_RED + "\nNão foi possível realizar a operação, o montante excede o valor de depósito possível!" + Color.ANSI_RESET);
        } else {
            balance += Double.parseDouble(amount);
            System.out.println(Color.ANSI_GREEN + "\nOperação efetuada com sucesso!" + Color.ANSI_RESET);
        }

        accountTransactionsCounter += 1;
    }

    public void withdraw() {
        String amount = "0";
        boolean isValid = true;

        do {
            if (isValid) {
                System.out.print("\nInsira o montante a levantar: ");
                amount = input.nextLine();
                isValid = false;
            } else {
                System.out.print(Color.ANSI_RED + "Montante inserido invalido, insira um montante valido: " + Color.ANSI_RESET);
                amount = input.nextLine();
            }
        } while (!Parse.tryParseDouble(amount));

        if (Double.parseDouble(amount) < 0) {
            System.out.println(Color.ANSI_RED + "\nNão foi possível realizar a operação, montante introduzido iválido!" + Color.ANSI_RESET);
        } else if ((Double.parseDouble(amount) > (balance + credit)) && (Double.parseDouble(amount) >= 0)) {
            System.out.println(Color.ANSI_RED + "\nNão foi possível realizar a operação, o montante excede o valor em conta + crédito!" + Color.ANSI_RESET);
        } else if (Double.parseDouble(amount) >= 0) {
            balance -= Double.parseDouble(amount);
            System.out.println(Color.ANSI_GREEN + "\nOperação efetuada com sucesso!" + Color.ANSI_RESET);
        }

        accountTransactionsCounter += 1;
    }

}
