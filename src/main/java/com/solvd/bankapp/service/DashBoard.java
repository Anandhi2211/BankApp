package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.LoginCredential;
import com.solvd.bankapp.exception.BankException;
import com.solvd.bankapp.service.DebitCardUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class DashBoard {
    private static final Logger logger = LogManager.getLogger(DashBoard.class);
    private final AccountUtil accountUtil;
    private final SavingAccountUtil savingAccountUtil;
    private final BankTransferUtil bankTransferUtil;
    private final TransactionUtil transactionUtil;
    private final DebitCardUtil debitCardUtil;
    Scanner in = new Scanner(System.in);

    public DashBoard() {
        this.bankTransferUtil = new BankTransferUtil();
        this.savingAccountUtil = new SavingAccountUtil();
        this.accountUtil = new AccountUtil();
        this.transactionUtil = new TransactionUtil();
        this.debitCardUtil = new DebitCardUtil();
    }

    public void welcomePage() {
        int answer;
        String userName = loginVerification();
        long accountNumber = accountUtil.getAccountNumber(userName) ;
        //find account number here from username from table
        if(userName!=null){
            do{
                logger.info("1. Account Details");
                logger.info("2. Saving Account");
                logger.info("3. Bank Transfers");
                logger.info("4. Transactions");
                logger.info("5. Debit Card");
                logger.info("6. Bill");
                logger.info("7. Log out");
                logger.info("Enter your options: ");
                answer = in.nextInt();
                if (!(answer >= 1) || !(answer <= 6)) {
                    throw new BankException("Invalid Input");
                }
                switch (answer){
                    case 1: {
                        accountUtil.displayAccountDetails(userName);//add DAO
                    }
                        break;
                    case 2:{
                        savingAccountUtil.savingAccountPage(accountNumber);//add DAO
                    }
                        break;
                    case 3:{
                        bankTransferUtil.bankTransferPage(accountNumber);//add DAO
                    }
                        break;
                    case 4:{
                        this.transactionUtil.transactionPage(accountNumber);

                        //need to chk  about transaction DAO code
                    }
                        break;
                    case 5:{
                        this.debitCardUtil.debitCardPage(accountNumber);

                    }
                        break;
                    case 6:{

                    }
                        break;
                    case 7:
                        logger.info("Exiting");
                        break;
                    default:
                        logger.info("Enter correct options");
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
