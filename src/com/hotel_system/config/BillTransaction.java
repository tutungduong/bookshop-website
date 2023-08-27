package com.hotel_system.config;

import com.hotel_system.constants.PaymentStatus;


import java.util.Date;

// BillTransaction is an abstract class
public abstract class BillTransaction {
    private Date creationDate;
    private double amount;
    private PaymentStatus status;

    public abstract void initiateTransaction();

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}

class CheckTransaction extends BillTransaction {
    private String bankName;
    private String checkNumber;

    public void initiateTransaction() {
        // functionality
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }
}

class CreditCardTransaction extends BillTransaction {
    private String nameOnCard;
    private int zipcode;

    public void initiateTransaction() {
        // functionality
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
}

class CashTransaction extends BillTransaction {
    private double cashTendered;

    public void initiateTransaction() {
        // functionality
    }

    public double getCashTendered() {
        return cashTendered;
    }

    public void setCashTendered(double cashTendered) {
        this.cashTendered = cashTendered;
    }
}