package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.Account;
import com.solvd.bankapp.exception.BankException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class PaymentUtil {
    Scanner in = new Scanner(System.in);

    private static final Logger logger = LogManager.getLogger(DashBoard.class);

    public void PayBillPage(Account account) {
        do {
            logger.info("1. Pay Bills");
            logger.info("2. Find Bill by ID");
            logger.info("3. Payed Bill History");

            logger.info("Enter your options: ");
            int answer = in.nextInt();
            if (!(answer >= 1) || !(answer <= 7)) {
                throw new BankException("Invalid Input");
            }
            switch (answer) {
            }


        } while (true);
    }
}