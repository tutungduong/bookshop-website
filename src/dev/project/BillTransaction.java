package dev.project;

// BillTransaction is an abstract class
public abstract class BillTransaction {
    private Date creationDate;
    private double amount;
    private Enums.PaymentStatus status;

    public abstract void initiateTransaction();
}

class CheckTransaction extends BillTransaction {
    private String bankName;
    private String checkNumber;

    public void initiateTransaction() {
        // functionality
    }
}

class CreditCardTransaction extends BillTransaction {
    private String nameOnCard;
    private int zipcode;

    public void initiateTransaction() {
        // functionality
    }
}

class CashTransaction extends BillTransaction {
    private double cashTendered;

    public void initiateTransaction() {
        // functionality
    }
}