package com.solvd.bankapp.util;

import com.solvd.bankapp.Main;
import com.solvd.bankapp.domain.Account;
import com.solvd.bankapp.domain.Customer;
import com.solvd.bankapp.domain.LoginCredential;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Scanner;

public class NewAccount {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public Customer addNewAccount(Customer customer) {
        Account account ;
        if(customer!=null){
            System.out.println("Deposit minimum balance of 100$");
            Scanner in = new Scanner(System.in);
            BigDecimal amt = in.nextBigDecimal();
            int answer = amt.compareTo(BigDecimal.valueOf(100));
            switch (answer){
                case 0:
                case 1:
                {
                    System.out.println("Account Created");
                    String userName = customer.getFirstName() + "_"+customer.getSsn();
                    account = new Account(1122334455,amt,userName);
                    customer.setAccount(account);
                    customer = setLoginDetails(account,customer);
                    break;
                }
                case -1:
                {
                    System.out.println("Enter minimum balance at least 100$");
                    System.out.println("Account Not Created");
                    customer = null;
                    break;
                }
            }
        }
        return customer;
    }

    public Customer setLoginDetails(Account account, Customer customer) {
        LoginCredential loginCredential = new LoginCredential();
        System.out.println("Enter NetBanking Password");
        Scanner in = new Scanner(System.in);
        String password = in.next();
        System.out.println("Retype the Password");
        String retypePassword = in.next();
        if(password.equals(retypePassword)){
            int pin = Integer.parseInt(Long.toString(customer.getSsn()).substring(Long.toString(customer.getSsn()).length() - 4 ));
            System.out.println(pin);
            loginCredential = new LoginCredential(account.getUserName(),password,true,pin,customer.getSsn());
//            customer.setAccount(account);
            customer.setLoginCredential(loginCredential);
        }
        else
        {
            System.out.println("Mismatch Password");
            account = new Account();
            customer.setAccount(account);
            customer = new Customer();
        }
        return customer;
    }
}
