package com.solvd.bankapp;

import com.solvd.bankapp.domain.Customer;
import com.solvd.bankapp.util.NewCustomer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.println("daaa sdfsdf!");

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the choices: ");
        System.out.println("1.Register");
        System.out.println("2.Login");
        System.out.println("1. Account Details");

        System.out.println("3.Deposit to ATM");
        System.out.println("4.Withdrawal from ATM");
        System.out.println("5.Exit");
        int answer = in.nextInt();
        switch (answer) {
            case 1: {
                NewCustomer newCustomer = new NewCustomer();
                Customer customer = newCustomer.addNewCustomer();
                if(customer!=null){
                    System.out.println(customer);
                    System.out.println(customer.getLoginCredential());
                    System.out.println(customer.getAccount());
                }
                else
                {
                    System.out.println("im here");
                }
                break;
            }
            case 2: {
                System.out.println("Exited");
                break;
            }
        }
    }
}