package com.finpulse.bankaccount_srevice.service;

import com.finpulse.bankaccount_srevice.DTO.BankAccountRequestDTO;
import com.finpulse.bankaccount_srevice.DTO.BankAccountResponseDTO;
import com.finpulse.bankaccount_srevice.entities.BankAccount;

import java.util.List;

public interface AccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
    public List<BankAccountResponseDTO> bankAccount();
    public BankAccountResponseDTO bankAccount(String id);
    public BankAccountResponseDTO update(String id , BankAccountRequestDTO bankAccountRequestDTO);
    public void delete(String id);
}
