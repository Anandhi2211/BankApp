package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.Account;
import com.solvd.bankapp.domain.SavingsAccount;
import com.solvd.bankapp.exception.BankException;
import com.solvd.bankapp.persistence.SavingsAccountDAO;
import com.solvd.bankapp.persistence.mybatis.SavingsAccountDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Scanner;

public class SavingAccountUtil {
    private final SavingsAccountDAO savingsAccountDAO;
    private final AccountUtil accountUtil;
    private final TransactionUtil transactionUtil;
    private static final Logger logger = LogManager.getLogger(SavingAccountUtil.class);
    public SavingAccountUtil() {
        this.savingsAccountDAO = new SavingsAccountDAOImpl();
        this.transactionUtil = new TransactionUtil();
        this.accountUtil = new AccountUtil();
    }
    public void savingAccountPage(String username, Scanner in) {
        do {
            Account account = accountUtil.getAccount(username);
            logger.info("1. Add amount to Saving Account");
            logger.info("2. Transfer amount from Saving Account");
            logger.info("3. Delete Savings Account");
            logger.info("4. Exit");
            logger.info("Enter your options: ");
            int answer = in.nextInt();
            if (!(answer >= 1) || !(answer <= 4)) {
                throw new BankException("Invalid Input");
            }
            switch (answer) {
                case 1:
                    logger.info("Enter the amount to be added to saving account");
                    BigDecimal amount = in.nextBigDecimal();
                    BigDecimal amountBalance = account.getTotalBalance();
                    if (amount.compareTo(amountBalance) == -1) {
                        int rate = 4;//can add in enum
                        SavingsAccount savingsAccount = this.savingsAccountDAO.findSavingByNumber(account.getAccountNumber());
                        if (savingsAccount == null) {
                            savingsAccount = new SavingsAccount(amount, account.getAccountNumber(), rate);
                            this.savingsAccountDAO.create(savingsAccount);
                            this.transactionUtil.addTransactions(account.getAccountNumber(), amount);
                            this.accountUtil.updateAmount(account.getAccountNumber(), amountBalance.subtract(amount));
                            logger.info("********* UPDATED AMT" + amountBalance.subtract(amount));
                        } else {
                            this.savingsAccountDAO.update(account.getAccountNumber(), amount.add(savingsAccount.getSavingsBalance()));
                            this.transactionUtil.addTransactions(account.getAccountNumber(), amount);
                            this.accountUtil.updateAmount(account.getAccountNumber(), amountBalance.subtract(amount));
                            logger.info("********* UPDATED AMT" + amountBalance.subtract(amount));
                        }
                    } else {
                        logger.info("Amount is not sufficient to add into saving account");
                    }
                    break;
                case 2:
                    logger.info("Enter the amount to transfer it to checking account");
                    amount = in.nextBigDecimal();
                    SavingsAccount savingsAccount = this.savingsAccountDAO.findSavingByNumber(account.getAccountNumber()); //code to get amount balance from saving table
                    if (savingsAccount != null) {
                        logger.info("TEST**********" + savingsAccount.getSavingsBalance());
                        if (amount.compareTo(savingsAccount.getSavingsBalance()) == -1) {
                            this.savingsAccountDAO.update(account.getAccountNumber(), savingsAccount.getSavingsBalance().subtract(amount));
                            this.transactionUtil.addTransactions(account.getAccountNumber(), amount);
                            this.accountUtil.updateAmount(account.getAccountNumber(), amount.add(account.getTotalBalance()));
                        } else {
                            logger.info("Amount is not sufficient to add into Checking account");
                        }
                    } else {
                        logger.info("No Savings Account");
                    }
                    break;
                case 3:
                    logger.info("Deleting Savings Account");
                    savingsAccount = this.savingsAccountDAO.findSavingByNumber(account.getAccountNumber());
                    this.accountUtil.updateAmount(account.getAccountNumber(), account.getTotalBalance().add(savingsAccount.getSavingsBalance()));
                    this.transactionUtil.addTransactions(account.getAccountNumber(), savingsAccount.getSavingsBalance());
                    this.savingsAccountDAO.delete(savingsAccount.getAccountNumber());
                    break;
                case 4:
                    logger.info("Exit");
                    return;
                default:
                    logger.info("Enter correct option");
                    return;
            }
        }
        while (true);
    }
}
