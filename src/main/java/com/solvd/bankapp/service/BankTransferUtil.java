package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.Account;
import com.solvd.bankapp.domain.Beneficiary;
import com.solvd.bankapp.exception.BankException;
import com.solvd.bankapp.persistence.BeneficiaryDAO;
import com.solvd.bankapp.persistence.mybatis.BeneficiaryDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class BankTransferUtil implements IBankTransfers {
    private final BeneficiaryDAO beneficiaryDAO;
    private final TransactionUtil transactionUtil;
    private static final Logger logger = LogManager.getLogger(DashBoard.class);
    Scanner in = new Scanner(System.in);

    public BankTransferUtil() {
        this.beneficiaryDAO = new BeneficiaryDAOImpl();
        this.transactionUtil = new TransactionUtil();
    }

    @Override
    public void bankTransferPage(Account account) {
        do{
            logger.info("1. Add Beneficiary");
            logger.info("2. View all Beneficiary");
            logger.info("3. Wire Transfer");
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
//                    account.setBeneficiaryList();
                    break;
                case 2:
                    boolean flag = false;
                    logger.info("List all Beneficiaries");

                    ArrayList<Beneficiary> beneficiaryList = (ArrayList<Beneficiary>) this.beneficiaryDAO.getAll();//this will fetch all the record of all the customer so we need to pass accounmber as parameter
                    for(Beneficiary beneficiary1 : beneficiaryList){ // we can modify the code
                        if(account.getAccountNumber() == beneficiary1.getAccountNumber()){
                            logger.info("Beneficiary Name: "+beneficiary1.getBeneficiaryName());
                            logger.info("Beneficiary Account Number: "+ beneficiary1.getBeneficiaryAccountNumber());
                            logger.info("***");
                            flag = true;
                        }
                    }
                    if(flag==false){
                        logger.info("No Beneficiary Account found");
                    }
                    break;
                case 3:
                    logger.info("Enter the Beneficiary Account Name: ");
                    String beneficiaryNameToTransfer = in.next();
                    beneficiaryList = (ArrayList<Beneficiary>) this.beneficiaryDAO.getAll();//this will fetch all the record of all the customer so we need to pass accounmber as parameter
                    for(Beneficiary beneficiary1 : beneficiaryList){
                        if(beneficiary1.getBeneficiaryName().equalsIgnoreCase(beneficiaryNameToTransfer)){
                            logger.info("Enter the amount to transfer: ");
                            BigDecimal amountToTransfer = in.nextBigDecimal();
                            if(amountToTransfer.compareTo(account.getTotalBalance()) == -1){
                                account.setTotalBalance(account.getTotalBalance().subtract(amountToTransfer));
                                transactionUtil.addTransactions(account.getAccountNumber(),amountToTransfer);
                                //update the updated amount to account DAO
                            }
                            else{
                                logger.info("Don't have enough Account Balance to do Transfer");
                            }
                        }
                        else{
                            logger.info("No Such Beneficiary Account");
                        }
                    }
                    break;
                case 4:
                    logger.info("Exiting");
                    break;
                default:
                    logger.info("Enter correct option");
                    return;
            }
        }while (true);
    }
}
