package com.solvd.bankapp.service.Impl;

import com.solvd.bankapp.service.DashBoard;
import com.solvd.bankapp.service.IBankTransfers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BankTransfers implements IBankTransfers {
    private static final Logger logger = LogManager.getLogger(DashBoard.class);
    @Override
    public void bankTransferPage(long accountNumber) {

        do{
            logger.info("1. Add Beneficiary");
            logger.info("2. View all Beneficiary");
            logger.info("3. ");


        }while (true);

    }
}