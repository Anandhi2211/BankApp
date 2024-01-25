package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.Account;
import com.solvd.bankapp.domain.DebitCard;
import com.solvd.bankapp.exception.BankException;
import com.solvd.bankapp.persistence.DebitCardDAO;
import com.solvd.bankapp.persistence.mybatis.DebitCardDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class DebitCardUtil {
    private static final Logger logger = LogManager.getLogger(DashBoard.class);
    Scanner in = new Scanner(System.in);
    private final DebitCardDAO debitCardDAO;
    private static long debitCardNumber = 1000000000;

    public DebitCardUtil() {
        this.debitCardDAO = new DebitCardDAOImpl();
    }

    public void debitCardPage(Account account) {
        do {
            logger.info("1. Create DebitCard");
            logger.info("2. Delete DebitCard");
            logger.info("3. Exit");
            int answer = in.nextInt();
            if (!(answer >= 1) || !(answer <= 3)) {
                throw new BankException("Invalid Input");
            }
            switch (answer) {
                case 1: {
                    logger.info("Create Debit Card");
                    //query to be added to get details li name and ssn
                    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                    LocalDate date = LocalDate.parse(currentTime.toString().substring(0,9));
                    date = date.plusYears(3);
                    Random random = new Random();
                    int cvv = random.nextInt(999);
                    DebitCard debitCard = new DebitCard(debitCardNumber++,Timestamp.valueOf(date.atStartOfDay()),cvv,"NAME",123654);
                    this.debitCardDAO.create(debitCard);
                }
                break;
                case 2:{
                    logger.info("Delete Debit Card");
                    long debitCardNumberToDelete = in.nextLong();
//                    this.debitCardDAO.delete(debitCardNumberToDelete);
                }
                    break;
                case 3:
                    logger.info("Exiting");
                    break;
                default:
                    logger.info("Enter valid option");
                    return;
            }


        }while (true);
    }
}
