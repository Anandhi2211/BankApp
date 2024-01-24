package com.solvd.bankapp.service;

import com.solvd.bankapp.exception.BankException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

public class DashBoard {
    private static final Logger logger = LogManager.getLogger(DashBoard.class);
    Scanner in = new Scanner(System.in);
    public void welcomePage() {
        int answer;
        String userName = loginVerification();
        if(userName!=null){
            do{
                logger.info("1.Account Details");
                logger.info("2.Create Saving Account");
                logger.info("3.Add Beneficiary Account");
                logger.info("4.Transactions");
                logger.info("5.Add Bill");
                logger.info("6.Bank to Bank Transfers");
                logger.info("7.Log out");
                answer = in.nextInt();
                if (!(answer >= 1) || !(answer <= 6)) {
                    throw new BankException("Invalid Input");
                }
                switch (answer){
                    case 1: {
                        AccountUtil accountUtil = new AccountUtil();
                        accountUtil.displayAccountDetails(userName);
                    }
                        break;
                    case 2:{
                        SavingAccountUtil savingAccountUtil = new SavingAccountUtil();
//                        savingAccountUtil.page();
                    }
                        break;
                    case 3:{

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
//        boolean flag;
        boolean found = false;
        logger.info("Enter user Name");

        String userName = in.next();
        //service layer to call to all usernames from login table
        //iterations from the
        ArrayList<String> usernameList = new ArrayList<>();
        usernameList.add("ajjj_1212121");
        usernameList.add("Anandhi_1234567");
        String passwordDb = "11";
        for(String uName : usernameList)
        {
            if(uName.equals(userName))
            {
                logger.info("Enter the password");
                String password = in.next();
                //service layer to match password in login tables
                if(passwordDb.equals(password)){//from DB
                    found = true;
                }
            }
        }
        if (!found){
            userName = "Not Found";
            logger.info("Login Details Error");        }
//        else{
//
////            flag = false;
//        }
        return userName;
    }
}
