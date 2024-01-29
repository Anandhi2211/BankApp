package com.solvd.bankapp.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NotificationService implements PaymentObserver {

    private static final Logger logger = LogManager.getLogger(NotificationService.class);

    @Override
    public void update(Payment payment) {
        if (payment.isNotificationStatus()) {
            // Need notification logic here?
            logger.info("Notification sent for payment with ID: " + payment.getTransactionId());
        }
    }
}
