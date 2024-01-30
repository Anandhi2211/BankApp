package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.*;
import com.solvd.bankapp.exception.BankException;
import com.solvd.bankapp.persistence.BankTransferDAO;
import com.solvd.bankapp.persistence.BeneficiaryDAO;
import com.solvd.bankapp.persistence.mybatis.BankTransferDAOImpl;
import com.solvd.bankapp.persistence.mybatis.BeneficiaryDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class BankTransferUtil implements IBankTransfers {
    private final BeneficiaryDAO beneficiaryDAO;
    private final TransactionUtil transactionUtil;
    private final AccountUtil accountUtil;
    private final BankTransferDAO bankTransferDAO;
    private static final Logger logger = LogManager.getLogger(BankTransferUtil.class);

    public BankTransferUtil() {
        this.beneficiaryDAO = new BeneficiaryDAOImpl();
        this.transactionUtil = new TransactionUtil();
        this.accountUtil = new AccountUtil();
        this.bankTransferDAO = new BankTransferDAOImpl();
    }

    @Override
    public void bankTransferPage(Account account, Scanner in) {
        do {
            account = this.accountUtil.getAccount(account.getUsername());
            logger.info("1. Add Beneficiary");
            logger.info("2. View all Beneficiary");
            logger.info("3. Bank to Bank Transfer");
            logger.info("4. Exit");
            int answer = in.nextInt();
            if (!(answer >= 1) || !(answer <= 4)) {
                throw new BankException("Invalid Input");
            }
            switch (answer) {
                case 1:
                    account = this.accountUtil.getAccount(account.getUsername());
                    logger.info("Enter Beneficiary Account Number");
                    long beneficiaryAccountNumber = in.nextLong();
                    logger.info("Enter Beneficiary Name");
                    String beneficiaryName = in.next();
                    Beneficiary beneficiary = Beneficiary.builder()
                            .setBeneficiaryName(beneficiaryName)
                            .setBeneficiaryAccountNumber(beneficiaryAccountNumber)
                            .setAccountNumber(account.getAccountNumber()).build();
                    this.beneficiaryDAO.create(beneficiary);
                    break;
                case 2:
                    logger.info("List all Beneficiaries");
                    ArrayList<Beneficiary> beneficiaryList = this.beneficiaryDAO.getAll(account.getAccountNumber());
                    if (beneficiaryList != null) {
                        for (Beneficiary beneficiary1 : beneficiaryList) {
                            if (account.getAccountNumber() == beneficiary1.getSourceAccountNumber()) {
                                logger.info("Beneficiary Name: " + beneficiary1.getBeneficiaryName());
                                logger.info("Beneficiary Account Number: " + beneficiary1.getBeneficiaryAccountNumber());
                                logger.info("***");
                            }
                        }
                    } else {
                        logger.info("No Beneficiary List");
                    }

                    break;
                case 3:
                    boolean flag = false;
                    logger.info("Enter the Beneficiary Account Name: ");
                    String beneficiaryNameToTransfer = in.next();
                    beneficiaryList = this.beneficiaryDAO.getAll(account.getAccountNumber());
                    if (beneficiaryList != null) {
                        for (Beneficiary beneficiary1 : beneficiaryList) {
                            if (beneficiary1.getBeneficiaryName().equalsIgnoreCase(beneficiaryNameToTransfer)) {
                                logger.info("Enter the amount to transfer: ");
                                BigDecimal amountToTransfer = in.nextBigDecimal();
                                if (amountToTransfer.compareTo(account.getTotalBalance()) == -1) {
                                    flag = true;
                                    BigDecimal transferCharge = new BigDecimal(10);
                                    Transaction transaction = this.transactionUtil.addTransactions(account.getAccountNumber(), amountToTransfer.add(transferCharge));
                                    this.accountUtil.updateAmount(account.getAccountNumber(), account.getTotalBalance().subtract(amountToTransfer.add(transferCharge)));
                                    BankTransfer bankTransfer = BankTransfer.builder()
                                            .transferId(transaction.getTransactionId())
                                            .transferDate(transaction.getTransactionTimestamp())
                                            .username(account.getUsername())
                                            .destinationAccount(beneficiary1.getSourceAccountNumber())
                                            .amount(amountToTransfer)
                                            .build();
                                    this.bankTransferDAO.create(bankTransfer);
//                                    }
                                } else {
                                    logger.info("Don't have enough Account Balance to do Transfer");
                                }
                            }
                        }
                        if (!flag) {
                            logger.info("Beneficiary Not found");
                        }
                    } else {
                        logger.info("No Beneficiary List");
                    }
                    break;
                case 4:
                    logger.info("Exit");
                    return;
                default:
                    logger.info("Enter correct option");
                    return;
            }
        } while (true);
    }
}
