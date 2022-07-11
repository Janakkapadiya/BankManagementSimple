package com.bankmanagerapp;
import com.bankmanagerapp.exceptions.NotSufficientAmountException;
public class Account {
    private int accountId;
    private String acc_holder;
    private int balance;

    private static int account_count = 1;

    public int getAccountId() {
        return accountId;

    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAcc_holder() {
        return acc_holder;
    }

    public void setAcc_holder(String acc_holder) {
        this.acc_holder = acc_holder;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Account(String acc_holder, int balance) {
        this.accountId = account_count;
        this.acc_holder = acc_holder;
        this.balance = balance;
        account_count++;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", acc_holder='" + acc_holder + '\'' +
                ", balance=" + balance +
                '}';
    }

    public void deposit(int amount)
    {
        this.balance =  this.balance + amount;
    }

    public void withdraw(int amount) throws NotSufficientAmountException
    {
        if (amount > balance) {
            throw new NotSufficientAmountException();
        }
        this.balance = this.balance - amount;
    }
}
