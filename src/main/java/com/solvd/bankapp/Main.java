package com.solvd.bankapp;

import com.solvd.bankapp.exception.BankException;
import com.solvd.bankapp.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        int answer;
        Scanner in = new Scanner(System.in);
        try {
            do {
                logger.info("Enter the choices: ");
                logger.info("1. Register");
                logger.info("2. Login");
                logger.info("3. Deposit to ATM");
                logger.info("4. Withdrawal from ATM");
                logger.info("5. Purchases with Debit Card");
                logger.info("6.Exit");
                answer = in.nextInt();
                if (!(answer >= 1) || !(answer <= 6)) {
                    throw new BankException("Invalid Input");
                }
                switch (answer) {
                    case 1: {
                        NewCustomer newCustomer = new NewCustomer();
                        newCustomer.addNewCustomer(in);
                        break;
                    }
                    case 2: {
                        DashBoard dashBoard = new DashBoard();
                        dashBoard.welcomePage(in);
                        break;
                    }
                    case 3: {
                        logger.info("Into Deposit");
                        AtmTransfersUtil depositUtil = new AtmTransfersUtil();
                        depositUtil.deposit(in);
                    }
                    break;
                    case 4: {
                        logger.info("Into Withdrawal");
                        AtmTransfersUtil depositUtil = new AtmTransfersUtil();
                        depositUtil.withdraw(in);
                    }
                    break;
                    case 5:
                        logger.info("PurchaseUtil product with Debit Card");
                        PurchaseUtil purchaseUtil = new PurchaseUtil();
                        purchaseUtil.purchaseItems(in);
                        break;
                    case 6:
                        logger.info("Thanks for Visiting");
                        return;
                    default:
                        logger.info("Enter the valid options");
                        return;
                }
            } while (true);
        } finally {
            in.close();
            logger.info("No Exception");
        }
    }
}