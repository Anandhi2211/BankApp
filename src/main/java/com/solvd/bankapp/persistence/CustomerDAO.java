package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.Customer;

public interface CustomerDAO extends BaseDAO<Customer> {
    @Override
    void create(Customer customer);

    void update(Customer customer);

    void delete(Customer customer);
}
