package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.*;
import com.solvd.bankapp.exception.BankException;
import com.solvd.bankapp.persistence.DebitCardDAO;
import com.solvd.bankapp.persistence.LoginCredentialDAO;
import com.solvd.bankapp.persistence.PurchaseProductDAO;
import com.solvd.bankapp.persistence.mybatis.DebitCardDAOImpl;
import com.solvd.bankapp.persistence.mybatis.LoginCredentialDAOImpl;
import com.solvd.bankapp.persistence.mybatis.PurchaseProductDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class PurchaseUtil {
    private static final Logger logger = LogManager.getLogger(DashBoard.class);
    private final DebitCardDAO debitCardDAO;
    private final PurchaseProductDAO purchaseProductDAO;
    private final AccountUtil accountUtil;
    private final TransactionUtil transactionUtil;

    public PurchaseUtil() {
        this.debitCardDAO = new DebitCardDAOImpl();
        this.accountUtil = new AccountUtil();
        transactionUtil = new TransactionUtil();
        purchaseProductDAO = new PurchaseProductDAOImpl();
    }

    public void purchaseItems(Scanner in) {
        do {
            logger.info("1. Purchase");
            logger.info("2. Purchase History");
            logger.info("3. Exit");
            int answer = in.nextInt();
            if (!(answer >= 1) || !(answer <= 3)) {
                throw new BankException("Invalid Input");
            }
            switch (answer) {
                case 1:
                    logger.info("Enter the debit card Number");
                    long cardNumber = in.nextLong();
                    DebitCard debitCard = this.debitCardDAO.findByCardNumber(cardNumber);
                    if (debitCard != null) {
                        if (cardNumber == debitCard.getCardNumber()) {
                            logger.info("Purchase Desc: ");
                            String desc = in.next();
                            logger.info("Enter the Amount:");
                            BigDecimal amt = in.nextBigDecimal();
                            logger.info("Enter the cvv");
                            int cvv = in.nextInt();
                            ArrayList<Account> accountList = this.debitCardDAO.findAccountBySsn(debitCard.getSsn());
                            if (accountList != null) {
                                for (Account account : accountList) {
                                    if (debitCard.getCvvNumber() == cvv) {
                                        if (amt.compareTo(account.getTotalBalance()) == -1) {
                                            this.accountUtil.updateAmount(account.getAccountNumber(), account.getTotalBalance().subtract(amt));
                                            Transaction transaction = this.transactionUtil.addTransactions(account.getAccountNumber(), amt);
//                                            PurchaseProduct purchaseProduct = new PurchaseProduct(debitCard.getCardNumber(), desc, amt, transaction.getTransactionId(), debitCard.getSsn(), transaction.getTransactionTimestamp());
                                            PurchaseProduct purchaseProduct = PurchaseProduct.builder()
                                                    .setPurchaseDescription(desc)
                                                    .setTransactionId(transaction.getTransactionId())
                                                    .setPurchaseTimestamp(transaction.getTransactionTimestamp())
                                                    .setAmount(amt)
                                                    .setCardNumber(debitCard.getCardNumber())
                                                    .setSsn(debitCard.getSsn())
                                                    .getPurchaseProduct();

                                            this.purchaseProductDAO.create(purchaseProduct);
                                            logger.info("Purchased Bill Generated to Purchase Table");
                                        } else {
                                            logger.info("Not Much Balance");
                                        }
                                    } else {
                                        logger.info("Cvv Don't Match");
                                    }
                                }
                            }
                        } else {
                            logger.info("Wrong Debit Card Number");
                        }
                    } else {
                        logger.info("No Debit Card List");
                    }
                    break;
                case 2: {
                    ArrayList<PurchaseProduct> purchaseProductList = this.purchaseProductDAO.getAll();
                    if (purchaseProductList != null) {
                        for (PurchaseProduct purchaseProduct : purchaseProductList) {
                            logger.info(purchaseProduct);
                        }
                    } else {
                        logger.info("No Purchase History");
                    }
                }
                return;
                default:
                    logger.info("Thanks for Shopping");
                    return;
            }
        } while (true);
    }
}
