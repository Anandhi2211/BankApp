package com.solvd.bankapp;

import com.solvd.bankapp.exception.BankException;
import com.solvd.bankapp.service.Impl.DashBoard;
import com.solvd.bankapp.service.Impl.NewCustomer;
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
                logger.info("daaa sdfsdf!");
                logger.info("Enter the choices: ");
                logger.info("1.Register");
                logger.info("2.Login");
                logger.info("3.Deposit to ATM");
                logger.info("4.Withdrawal from ATM");
                logger.info("5.Exit");
                answer = in.nextInt();
                if (!(answer >= 1) || !(answer <= 5)) {
                    throw new BankException("Invalid Input");
                }
                switch (answer) {
                    case 1: {
                        NewCustomer newCustomer = new NewCustomer();
                        newCustomer.addNewCustomer();
                        break;
                    }
                    case 2: {
                        DashBoard dashBoard = new DashBoard();
                        dashBoard.welcomePage();
                        break;
                    }
                    case 3: {
                        logger.info("Into Deposit");
                    }
                    break;
                    case 4: {
                        logger.info("Into Withdrawal");
                    }
                    break;
                    case 5:
                        logger.info("Thanks for Visiting");
                        return;
                    default:
                        logger.info("Enter the valid options");
                }
            } while (true);
        } finally {
            in.close();
            logger.info("No Exception");
        }
    }
}