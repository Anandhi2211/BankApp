package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.Customer;
import com.solvd.bankapp.service.ICustomer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NewCustomer implements ICustomer {
    private static final Logger logger = LogManager.getLogger(NewCustomer.class);

    public void addNewCustomer() {
        logger.info("New ICustomer details");
       //Customer customer = new Customer(1234567, "Anandhi", "Jayapal", "anandhirmk@gmail.com", "9999999999");
      // create customer using Builder pattern
        Customer customer1 = Customer.builder()
                .FirstName("Anandhi").LastName("Jayapal").PhoneNumber("9999999999").Email("anandhirmk@gmail.com").getCustomer();
        com.solvd.bankapp.service.Impl.AccountUtil accountUtil = new com.solvd.bankapp.service.Impl.AccountUtil();
        accountUtil.createAccount(customer1);
    }
}
