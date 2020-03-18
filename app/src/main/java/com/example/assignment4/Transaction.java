package com.example.assignment4;

import androidx.annotation.NonNull;

import java.util.Date;

public class Transaction {
    String date, reason;
    double amount;
    boolean add;

    public Transaction(double amount_in, String date_in, String reason_in, boolean add_in){
        this.amount = amount_in;
        this.date = date_in;
        this.reason = reason_in;
        this.add = add_in;
    }

    @NonNull
    @Override
    public String toString() {
        String format = "";
        if(this.add){
            format = "Added $" + this.amount +
                    " on " + this.date +
                    " from " + this.reason;
        }
        else{
            format = "Spent $" + this.amount +
                    " on " + this.date +
                    " for " + this.reason;
        }
        return format;
    }
}
