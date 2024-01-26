package com.solvd.bankapp.service;
import com.solvd.bankapp.service.AccountUtil;
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
    Scanner in = new Scanner(System.in);

    public SavingAccountUtil() {
        this.savingsAccountDAO = new SavingsAccountDAOImpl();
        this.transactionUtil = new TransactionUtil();
        this.accountUtil = new AccountUtil();
    }

    public void savingAccountPage(long accountNumber) {
        do{
            logger.info("1. Add amount to Saving Account");
            logger.info("2. Transfer amount from Saving Account");
            logger.info("3. Exit");
            logger.info("Enter your options: ");
            int answer = in.nextInt();
            if (!(answer >= 1) || !(answer <= 2)) {
                throw new BankException("Invalid Input");
            }
            switch (answer){
                case 1:
                    logger.info("Enter the amount to be added to saving account");
                    BigDecimal amount = in.nextBigDecimal();
                    BigDecimal amountBalance = BigDecimal.valueOf(0); //code to get amount balance
                    if(amount.compareTo(amountBalance) == -1 ){
                        int rate = 4;//can add in enum
                        SavingsAccount savingsAccount = new SavingsAccount(amount,accountNumber,rate);
                        this.savingsAccountDAO.create(savingsAccount);
                        this.transactionUtil.addTransactions(accountNumber,amount);
//                        this.accountUtil.update(accountNumber,amountBalance.subtract(amount));//created code
                    }
                    else{
                        logger.info("Amount is not sufficient to add into saving account");
                    }
                    break;
                case 2:
                    logger.info("Enter the amount to change it to checking account");
                    amount = in.nextBigDecimal();
                    amountBalance = BigDecimal.valueOf(0); //code to get amount balance
                    if(amount.compareTo(amountBalance) == -1 ){
//                        this.savingsAccountDAO.update
                        this.transactionUtil.addTransactions(accountNumber,amount);
//                        this.accountUtil.update(accountNumber,amountBalance.subtract(amount));//created code
                    }
                    else{
                        logger.info("Amount is not sufficient to add into Checking account");
                    }
                    break;
                case 3:
                    logger.info("Exiting");
                    break;
                default:
                    logger.info("Enter correct option");
                    break;
            }
        }
        while (true);
    }
}