package com.finpulse.bankaccount_srevice.repositories;

import com.finpulse.bankaccount_srevice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
