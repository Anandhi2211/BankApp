package com.solvd.bankapp.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class NotificationService implements PaymentObserver {

    private static final Logger logger = LogManager.getLogger(NotificationService.class);

    @Override
    public void update(Account account) {
        BigDecimal threshold = new BigDecimal("100");
        if (account.isBalanceBelowThreshold(threshold)) {
            logger.info("Notification sent for account with balance below $100: " + account.getAccountNumber());
        }
    }
}
