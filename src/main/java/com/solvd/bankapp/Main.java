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
        int answer = in.nextInt();

        switch (answer){
            case 1:
            {
                NewCustomer newCustomer = new NewCustomer();
                Customer customer = newCustomer.addNewCustomer();
                    System.out.println(customer);
                    System.out.println(customer.getLoginCredential());
                    System.out.println(customer.getAccount());
                break;
            }
        }
    }
}