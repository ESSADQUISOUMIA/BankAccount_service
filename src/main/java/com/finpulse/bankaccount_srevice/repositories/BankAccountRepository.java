package com.finpulse.bankaccount_srevice.repositories;

import com.finpulse.bankaccount_srevice.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
