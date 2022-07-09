package com.BakManagerApp;

import com.BakManagerApp.exceptions.AccountNotFoundException;
import com.BakManagerApp.exceptions.NotSufficientAmountException;

import java.util.HashMap;

public class Bank {
       private static Bank INSTANCE;
       private final HashMap<Integer,Account> accounts;
       private Bank()
       {
           this.accounts = new HashMap<>();
       }

       public static Bank getInstance() {
              if (INSTANCE == null) {
                     INSTANCE = new Bank();
              }
              return INSTANCE;
       }

       public int getBalance(int accountId) throws AccountNotFoundException
       {
              Account ac = getAccountById(accountId);
              return ac.getBalance();
       }

       public Account getAccountById(int accountId) throws AccountNotFoundException {
              Account ac = accounts.get(accountId);
              if (ac == null) {
                     throw new AccountNotFoundException();
              }
              return ac;
       }

       public void withdraw(int accountId, int amount)
               throws NotSufficientAmountException, AccountNotFoundException
       {
              Account ac =  getAccountById(accountId);
              ac.withdraw(amount);
       }

       public void deposit(int accountId,int amount) throws AccountNotFoundException {
              Account ac = getAccountById(accountId);
              ac.deposit(amount);
       }

       public int createAccount(String name, int initialDeposit) {
              Account ac = new Account(name, initialDeposit);
              accounts.put(ac.getAccountId(), ac);
              return ac.getAccountId();
       }

}
