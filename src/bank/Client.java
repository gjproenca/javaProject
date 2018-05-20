package bank;

public class Client {

    public Client(String custumerName, String custumerPhoneNumber) {
        this.custumerName = custumerName;
        this.custumerPhoneNumber = custumerPhoneNumber;
    }

    private String custumerName;
    private String custumerPhoneNumber;

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

}
