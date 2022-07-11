package com.bankmanagerapp;

import com.bankmanagerapp.exceptions.AccountNotFoundException;
import com.bankmanagerapp.exceptions.NotSufficientAmountException;

import java.util.Scanner;
import java.util.logging.Logger;

public class Application {
    public static void main(String[] args) throws AccountNotFoundException, NotSufficientAmountException {
        Bank bank = Bank.getInstance();
        String choice;
        int idInput;
        int amount;
        boolean flag = true;
        Logger logger = Logger.getLogger(Application.class.getName());
        Scanner ac = new Scanner(System.in);
        String name;
        while (flag) {
            logger.config(" \n Select a choice:  ");
            logger.config("1. Existing customer ");
            logger.config("2. New customer ");
            logger.config("3. Quit ");
            String input = ac.next();
            switch (input) {
                case "1" -> {
                        logger.config("enter customer id : ");
                        idInput = ac.nextInt();
                    try {
                        bank.checkIsAccountExist(idInput);
                        logger.config("Would you like to: ");
                        logger.config("1. Deposit ");
                        logger.config("2. Withdraw ");
                        logger.config("3. Check balance ");
                        choice = ac.next();
                        switch (choice) {
                            case "1" -> {
                                logger.config("How much would you like to deposit?  ");
                                amount = ac.nextInt();
                                bank.deposit(idInput, amount);
                                int latestBalance = bank.getBalance(idInput);
                                logger.fine("Updated balance is " + latestBalance);
                            }
                            case "2" -> {
                                logger.config("How much would you like to withdraw?  ");
                                amount = ac.nextInt();
                                try {
                                    bank.withdraw(idInput, amount);
                                    int latestBalance = bank.getBalance(idInput);
                                    logger.config("Updated balance is " + latestBalance);
                                } catch (NotSufficientAmountException e) {
                                    logger.config(e.getMessage());
                                }
                            }
                            case "3" ->
                            {
                                logger.config("your balance is:  ");
                                int storedBalance = bank.getBalance(idInput);
                                logger.config(String.valueOf(storedBalance));
                            }

                            default -> logger.config("Invalid");
                        }
                    } catch (AccountNotFoundException e) {
                        logger.config(e.getMessage());
                    }
                }
                    case "2" -> {
                        //add new user
                        logger.config("Enter your name: ");
                        name = ac.next();
                        logger.config("Enter initial balance: ");
                        amount = ac.nextInt();
                        int createdNewAccount = bank.createAccount(name,amount);
                        logger.config("Your customer ID will be: " + createdNewAccount);
                    }

                    case "3" -> {
                        logger.config("Thanks for using our service");
                        flag = false;
                    }

                    default -> logger.config("Invalid");
                }
            }
        }
    }

