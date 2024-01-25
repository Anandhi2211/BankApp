package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.LoginCredential;
import com.solvd.bankapp.service.Impl.AccountUtil;
import com.solvd.bankapp.service.Impl.BankTransfers;
import com.solvd.bankapp.exception.BankException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class DashBoard {
    private static final Logger logger = LogManager.getLogger(DashBoard.class);
    Scanner in = new Scanner(System.in);
    public void welcomePage() {
        int answer;
        String userName = loginVerification();
        long accountNumber = 0 ;
        //find account number here from username from table
        if(userName!=null){
            do{

                logger.info("1.Account Details");
                logger.info("2.Saving Account");
                logger.info("3.Bank Transfers");
                logger.info("4.Transactions");
                logger.info("5.Bill");
                logger.info("6.");
                logger.info("7.Log out");
                logger.info("Enter your options: ");
                answer = in.nextInt();
                if (!(answer >= 1) || !(answer <= 6)) {
                    throw new BankException("Invalid Input");
                }
                switch (answer){
                    case 1: {
                        com.solvd.bankapp.service.Impl.AccountUtil accountUtil = new AccountUtil();
                        accountUtil.displayAccountDetails(userName);
                    }
                    break;
                    case 2:{
                        SavingAccountUtil savingAccountUtil = new SavingAccountUtil();
                        savingAccountUtil.savingAccountPage(accountNumber);
                    }
                    break;
                    case 3:{
                        BankTransfers bankTransfers = new BankTransfers();
                        bankTransfers.bankTransferPage(accountNumber);
                    }
                    break;
                    case 4:{



                    }
                    break;
                    case 5:{

                    }
                    break;
                    case 6:
                        break;
                    case 7:
                        break;
                    default:
                        logger.info("Log out");
                        return;
                }

            }while (true);

        }
        else{
            logger.info("Error with the LoginDetails");
        }
    }
    private String loginVerification( ) {
        logger.info("Enter user Name");
        String username = in.next();
        LoginCredential loginCredential = new LoginCredential(); // code fetch from login table
        if(loginCredential!=null){
            if(loginCredential.getUsername().equals(username)){
                logger.info("Enter the password");
                String password = in.next();
                if (loginCredential.getUserPassword().equals(password)){
                    logger.info("Login Success");
                }
                else{
                    logger.info("Password wrong");
                }
            }
        }
        else {
            logger.info("username not found");
        }

        return username;
    }
}