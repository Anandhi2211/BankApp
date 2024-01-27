package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.Account;
import com.solvd.bankapp.domain.BankTransfer;
import com.solvd.bankapp.domain.Beneficiary;
import com.solvd.bankapp.domain.Transaction;
import com.solvd.bankapp.exception.BankException;
import com.solvd.bankapp.persistence.BankTransferDAO;
import com.solvd.bankapp.persistence.BeneficiaryDAO;
import com.solvd.bankapp.persistence.mybatis.BankTransferDAOImpl;
import com.solvd.bankapp.persistence.mybatis.BeneficiaryDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BankTransferUtil implements IBankTransfers {
    private final BeneficiaryDAO beneficiaryDAO;
    private final TransactionUtil transactionUtil;
    private final AccountUtil accountUtil;
    private final BankTransferDAO bankTransferDAO;
    private static final Logger logger = LogManager.getLogger(DashBoard.class);
    Scanner in = new Scanner(System.in);

    public BankTransferUtil() {
        this.beneficiaryDAO = new BeneficiaryDAOImpl();
        this.transactionUtil = new TransactionUtil();
        this.accountUtil = new AccountUtil();
        bankTransferDAO = new BankTransferDAOImpl();
    }

    @Override
    public void bankTransferPage(Account account) {
        do{
            account = this.accountUtil.getAccount(account.getUsername());
            logger.info("1. Add Beneficiary");
            logger.info("2. View all Beneficiary");
            logger.info("3. Bank to Bank Transfer");
            logger.info("4. Exit");
            int answer = in.nextInt();
            if (!(answer >= 1) || !(answer <= 4)) {
                throw new BankException("Invalid Input");
            }
            switch (answer){
                case 1:
                    logger.info("Enter Beneficiary Account Number");
                    long beneficiaryAccountNumber = in.nextLong();
                    logger.info("Enter Beneficiary Name");
                    String beneficiaryName = in.next();
                    Beneficiary beneficiary = new Beneficiary(beneficiaryName,beneficiaryAccountNumber,account.getAccountNumber());
                    this.beneficiaryDAO.create(beneficiary);
                    break;
                case 2:
                    logger.info("List all Beneficiaries");
                    ArrayList <Beneficiary> beneficiaryList = this.beneficiaryDAO.getAll(account.getAccountNumber());
                    if(beneficiaryList!=null){
                        for(Beneficiary beneficiary1 : beneficiaryList){ // we can modify the code
                            if(account.getAccountNumber() == beneficiary1.getAccountNumber()){
                                logger.info("Beneficiary Name: "+beneficiary1.getBeneficiaryName());
                                logger.info("Beneficiary Account Number: "+ beneficiary1.getBeneficiaryAccountNumber());
                                logger.info("***");
                            }
                        }
                    }
                    else{
                        logger.info("No Beneficiary List");
                    }
                    break;
                case 3:
                    boolean flag = false;
                    logger.info("Enter the Beneficiary Account Name: ");
                    String beneficiaryNameToTransfer = in.next();
                    beneficiaryList = this.beneficiaryDAO.getAll(account.getAccountNumber());
                    if(beneficiaryList!=null){
                        for(Beneficiary beneficiary1 : beneficiaryList){
                            if(beneficiary1.getBeneficiaryName().equalsIgnoreCase(beneficiaryNameToTransfer)){
                                logger.info("Enter the amount to transfer: ");
                                BigDecimal amountToTransfer = in.nextBigDecimal();
                                if(amountToTransfer.compareTo(account.getTotalBalance()) == -1){
                                    flag=true;
                                    Transaction transaction = transactionUtil.addTransactions(account.getAccountNumber(),amountToTransfer);
                                    accountUtil.updateAmount(account.getAccountNumber(),account.getTotalBalance().subtract(amountToTransfer));
                                    BankTransfer bankTransfer = new BankTransfer(amountToTransfer,beneficiary1.getAccountNumber(),new BigDecimal(10),account.getUsername(),transaction.getTransactionId());
                                    logger.info(bankTransfer);
                                    logger.info(bankTransfer.getTransferAmount());
                                    logger.info(bankTransfer.getCharge());
                                    logger.info(bankTransfer.getUsername());
                                    logger.info(bankTransfer.getBeneficiaryAccountNumber());
                                    logger.info(bankTransfer.getUsername());
                                    bankTransferDAO.create(bankTransfer);
                                }
                                else{
                                    logger.info("Don't have enough Account Balance to do Transfer");
                                }
                            }
                            if(!flag){
                                logger.info("Beneficiary Not found");
                            }
                        }
                    }
                    else{
                        logger.info("No Beneficiary List");
                    }
                    break;
                case 4:
                    logger.info("Exiting");
                    return;
                default:
                    logger.info("Enter correct option");
                    return;
            }
        }while (true);
    }
}
