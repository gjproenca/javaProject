package bankapp;

import java.util.Scanner;

public class Account {

    //constructor
    public Account(int accountNumber, String custumerName, String custumerPhoneNumber, String nib, double balance, double credit) {
        this.accountNumber = accountNumber;
        this.custumerName = custumerName;
        this.custumerPhoneNumber = custumerPhoneNumber;
        this.nib = nib;
        this.balance = balance;
        this.credit = credit;
    }

    public Account() {
    }

    //atributes
    private final Scanner input = new Scanner(System.in);
    private int accountNumber = 0;
    private String custumerName;
    private String custumerPhoneNumber;
    private String nib;
    private double balance = 0;
    private double credit = 0;
    private int accoutOperationsCounter = 0;
    private boolean valid = true;

    //getters and setters
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getCustumerName() {
        return custumerName;
    }

    public void setCustumerName(String custumerName) {
        this.custumerName = custumerName;
    }

    public String getCustumerPhoneNumber() {
        return custumerPhoneNumber;
    }

    public void setCustumerPhoneNumber(String custumerPhoneNumber) {
        this.custumerPhoneNumber = custumerPhoneNumber;
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

    public int getAccoutOperationsCounter() {
        return accoutOperationsCounter;
    }

    //methods
    public void deposit() {
        String amount = "0";
        boolean valid = true;

        do {
            if (valid) {
                System.out.print("\nInsira o montante a depositar: ");
                amount = input.nextLine();
                valid = false;
            } else {
                System.out.print("Montante inserido invalido, insira um montante valido: ");
                amount = input.nextLine();
            }
        } while (!Parse.tryParseDouble(amount));

        if (Double.parseDouble(amount) < 0) {
            System.out.println("\nNão foi possível realizar a operação, o montante excede o valor em conta + crédito!");
        } else if (Double.parseDouble(amount) >= 0) {
            balance += Double.parseDouble(amount);
            System.out.println("\nOperação efetuada com sucesso!");
        }

        this.accoutOperationsCounter += 1;
    }

    public void withdraw() {
        String amount = "0";
        boolean valid = true;

        do {
            if (valid) {
                System.out.print("\nInsira o montante a levantar: ");
                amount = input.nextLine();
                valid = false;
            } else {
                System.out.print("Montante inserido invalido, insira um montante valido: ");
                amount = input.nextLine();
            }
        } while (!Parse.tryParseDouble(amount));

        if ((Double.parseDouble(amount) > (balance + credit)) && (Double.parseDouble(amount) >= 0)) {
            System.out.println("\nNão foi possível realizar a operação, o montante excede o valor em conta + crédito!");
        } else if (Double.parseDouble(amount) >= 0) {
            balance -= Double.parseDouble(amount);
            System.out.println("\nOperação efetuada com sucesso!");
        }

        accoutOperationsCounter += 1;
    }

}
