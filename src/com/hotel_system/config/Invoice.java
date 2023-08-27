package com.hotel_system.config;

public class Invoice {
    private double amount;

    public boolean createBill(){
        return false;
    }
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}