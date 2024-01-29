package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.Account;
import com.solvd.bankapp.domain.LoginCredential;
import com.solvd.bankapp.exception.BankException;
import com.solvd.bankapp.persistence.LoginCredentialDAO;
import com.solvd.bankapp.persistence.mybatis.LoginCredentialDAOImpl;
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
    private final PaymentUtil paymentUtil;
    private final LoginCredentialDAO loginCredentialDAO;
    Scanner in = new Scanner(System.in);

    public DashBoard() {
        this.bankTransferUtil = new BankTransferUtil();
        this.savingAccountUtil = new SavingAccountUtil();
        this.accountUtil = new AccountUtil();
        this.transactionUtil = new TransactionUtil();
        this.debitCardUtil = new DebitCardUtil();
        this.loginCredentialDAO = new LoginCredentialDAOImpl();
        this.paymentUtil = new PaymentUtil();
    }

    public void welcomePage() {
        int answer;
        String userName = loginVerification();
        if (userName != null) {
            do {
                Account account = accountUtil.getAccount(userName);
                logger.info("1. Account Details");
                logger.info("2. Saving Account");
                logger.info("3. Bank Transfers");
                logger.info("4. Transactions");
                logger.info("5. Debit Card");
                logger.info("6. Bill");
                logger.info("7. Log out");
                logger.info("Enter your options: ");
                answer = in.nextInt();
                if (!(answer >= 1) || !(answer <= 7)) {
                    throw new BankException("Invalid Input");
                }
                switch (answer) {
                    case 1: {
                        accountUtil.displayAccountDetails(account.getUsername());
                    }
                    break;
                    case 2: {
                        savingAccountUtil.savingAccountPage(account.getUsername());
                    }
                    break;
                    case 3: {
                        bankTransferUtil.bankTransferPage(account);
                    }
                    break;
                    case 4: {
                        this.transactionUtil.transactionPage(account);
                    }
                    break;
                    case 5: {
                        this.debitCardUtil.debitCardPage(account);
                    }
                    break;
                    case 6: {
                        this.paymentUtil.PayBillPage(account);
                        logger.info("BILLS");
                    }
                    break;
                    case 7:
                        logger.info("Exiting");
                        return;
                    default:
                        logger.info("Enter correct options");
                        return;
                }
            } while (true);
        } else {
            logger.info("Error with the LoginDetails");
        }
    }

    private String loginVerification() {
        logger.info("Enter user Name");
        String username = in.next();
        LoginCredential loginCredential = loginCredentialDAO.findByUsername(username);
        if (loginCredential != null) {
            if (loginCredential.getUsername().equals(username)) {
                logger.info("Enter the password");
                String password = in.next();
                if (loginCredential.getUserPassword().equals(password)) {
                    logger.info("Login Success");
                } else {
                    logger.info("Password wrong");
                }
            }
        } else {
            logger.info("username not found");
            username = null;
        }
        return username;
    }
}
