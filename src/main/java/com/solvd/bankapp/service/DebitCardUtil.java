package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.Account;
import com.solvd.bankapp.domain.Customer;
import com.solvd.bankapp.domain.DebitCard;
import com.solvd.bankapp.exception.BankException;
import com.solvd.bankapp.persistence.CustomerDAO;
import com.solvd.bankapp.persistence.DebitCardDAO;
import com.solvd.bankapp.persistence.mybatis.CustomerDAOImpl;
import com.solvd.bankapp.persistence.mybatis.DebitCardDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class DebitCardUtil {
    private static final Logger logger = LogManager.getLogger(DashBoard.class);
    private final DebitCardDAO debitCardDAO;
    private final CustomerDAO customerDAO;
    private static long debitCardNumber = 1004586965;

    public DebitCardUtil() {
        this.debitCardDAO = new DebitCardDAOImpl();
        this.customerDAO = new CustomerDAOImpl();
    }

    public void debitCardPage(Account account, Scanner in) {
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
                    Customer customer = this.customerDAO.display(account.getUsername());
                    if (this.debitCardDAO.findBySsn(customer.getSsn()) == null) {
                        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                        LocalDate date = LocalDate.parse(currentTime.toString().substring(0, 10));
                        date = date.plusYears(3);
                        Random random = new Random();
                        int cvv = random.nextInt(999);
//                        DebitCard debitCard = new DebitCard(debitCardNumber++, Timestamp.valueOf(date.atStartOfDay()), cvv, customer.getFirstName(), customer.getSsn());

                        DebitCard debitCard = DebitCard.builder().setCardNumber(debitCardNumber++)
                                .setExpirationDate(Timestamp.valueOf(date.atStartOfDay()))
                                .setCvvNumber(cvv)
                                .setSsn(customer.getSsn())
                                .setCustomerFullName(customer.getFirstName()+" "+customer.getLastName()).build();

//                                (debitCardNumber++, Timestamp.valueOf(date.atStartOfDay()), cvv, customer.getFirstName(), customer.getSsn());


                        logger.info(debitCard);
                        this.debitCardDAO.create(debitCard);
                    } else {
                        logger.info("CARD ALREADY exist");
                    }
                }
                break;
                case 2: {
                    logger.info("Delete Debit Card");
                    logger.info("Enter Debit Card Number:");
                    long debitCardNumberToDelete = in.nextLong();
                    DebitCard debitCard = this.debitCardDAO.findByCardNumber(debitCardNumberToDelete);
                    if (debitCard != null) {
                        if (debitCard.getCardNumber() == debitCardNumberToDelete) {
                            this.debitCardDAO.delete(debitCardNumberToDelete);
                        }
                    } else {
                        logger.info("No Debit Card Found");
                    }
                }
                break;
                case 3:
                    logger.info("Exit");
                    return;
                default:
                    logger.info("Enter valid option");
                    return;
            }
        } while (true);
    }
}
