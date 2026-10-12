package com.finpulse.bankaccount_srevice.entities;

import com.finpulse.bankaccount_srevice.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

@Projection(types=BankAccount.class, name="p1")
public interface AccountProjection {
    public String getId();
    public AccountType getType();

}