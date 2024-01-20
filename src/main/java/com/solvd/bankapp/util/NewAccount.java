package com.solvd.bankapp.util;

import com.solvd.bankapp.Main;
import com.solvd.bankapp.domain.Account;
import com.solvd.bankapp.domain.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Scanner;

public class NewAccount {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public Account addNewAccount(Customer customer) {
        Account account = new Account();
        if(customer!=null){
            logger.info("Enter the minmum balance of 100$");
            Scanner in = new Scanner(System.in);
//            BigDecimal amt = in.nextBigDecimal();
            BigDecimal amt = BigDecimal.valueOf(100);

            int answer = amt.compareTo(BigDecimal.valueOf(100));
            switch (answer){
                case 0:
                case 1:
                {
                    String userName = customer.getFirstName() + "_"+customer.getSsn();
                    account = new Account(1122334455,amt,userName);
                    break;
                }
                case -1:
                {
                    logger.info("Enter minimum balance at least 100$");
                    break;
                }
            }
//            if((amt.compareTo(BigDecimal.valueOf(100)) == 0 ) && (amt.compareTo(BigDecimal.valueOf(100)) ==1)){
//
//            }
//            else {
//            }
        }
        return account;
    }
}
