package bankapp;

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
    private int accountNumber = 0;
    private String custumerName;
    private String custumerPhoneNumber;
    private String nib;
    private double balance = 0;
    private double credit = 0;
    private int accoutOperationsCounter = 0;

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
    public void deposit(double amount) {
        this.balance += amount;
        this.accoutOperationsCounter += 1;
    }

    public void withdraw(double amount) {
    }
    
}
