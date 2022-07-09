package com.BakManagerApp;

import com.BakManagerApp.exceptions.AccountNotFoundException;
import com.BakManagerApp.exceptions.NotSufficientAmountException;

import java.util.Scanner;

import static com.BakManagerApp.Account.account_count;

public class Application {
    public static void main(String[] args) throws AccountNotFoundException, NotSufficientAmountException {
        Bank bank = Bank.getInstance();
        String choice;
        int amount;
        int id;
        boolean flag = true;
        Scanner ac = new Scanner(System.in);
        String name;
        while (flag) {
            System.out.println("Select a choice:");
            System.out.println("1. Existing customer");
            System.out.println("2. New customer");
            System.out.println("3. Quit");

            String input = ac.next();

            id = account_count;

            switch (input) {
                case "1" -> {
                    System.out.print("enter customer id : ");
                        bank.getAccountById(id);
                        System.out.println("Would you like to: ");
                        System.out.println("1. Deposit ");
                        System.out.println("2. Withdraw ");
                        System.out.println("3. Check balance ");
                        choice = ac.next();
                        switch (choice) {
                            case "1" -> {
                                System.out.println("How much would you like to deposit?");
                                amount = ac.nextInt();
                                bank.deposit(id, amount);
                            }
                            case "2" -> {
                                System.out.println("How much would you like to withdraw?");
                                amount = ac.nextInt();
                                bank.withdraw(id, amount);

                            }
                            case "3" ->
                            {
                                System.out.println("your balance is:");
                                int StoredBalance = bank.getBalance(id);
                                System.out.print(StoredBalance);
                            }

                            default -> System.out.println("Invalid");
                        }
                    }
                    case "2" -> {
                        //add new user
                        System.out.println("Enter your name: ");
                        name = ac.next();
                        System.out.println("Enter initial balance: ");
                        amount = ac.nextInt();
                        bank.deposit(id,amount);
                        int createdNewAccount = bank.createAccount(name,amount);
                        System.out.print(createdNewAccount);
                        System.out.println("Your customer ID will be: " + id);
                    }

                    case "3" -> {
                        System.out.println("Thanks for using our service");
                        flag = false;
                    }

                    default -> System.out.println("Invalid");
                }
            }
        }
    }

