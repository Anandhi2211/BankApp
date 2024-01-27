package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.Customer;
import org.apache.ibatis.annotations.Param;

public interface CustomerDAO extends BaseDAO<Customer> {
    @Override
    void create(@Param("customer") Customer customer);

    void update(Customer customer);

    void delete(Customer customer);

    Customer display(@Param("username") String username);
}
