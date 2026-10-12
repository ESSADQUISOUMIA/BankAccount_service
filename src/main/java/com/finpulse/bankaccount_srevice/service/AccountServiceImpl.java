package com.finpulse.bankaccount_srevice.service;

import com.finpulse.bankaccount_srevice.DTO.BankAccountRequestDTO;
import com.finpulse.bankaccount_srevice.DTO.BankAccountResponseDTO;
import com.finpulse.bankaccount_srevice.entities.BankAccount;
import com.finpulse.bankaccount_srevice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
    @Autowired
    private BankAccountRepository bankAccountRepository;


    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .currency(bankAccountDTO.getCurrency())
                .type(bankAccountDTO.getType())
                .build();
        BankAccount saveBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = BankAccountResponseDTO.builder()
                .id(saveBankAccount.getId())
                .type(saveBankAccount.getType())
                .balance(saveBankAccount.getBalance())
                .currency(saveBankAccount.getCurrency())
                .createAt(saveBankAccount.getCreateAt())
                .build();
        return bankAccountResponseDTO;

    }

    @Override
    public List<BankAccountResponseDTO> bankAccount() {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        List<BankAccountResponseDTO> bankAccountResponseDTOS = new ArrayList<>();
        bankAccounts.forEach(bankAccount -> {
            BankAccountResponseDTO bankAccountResponseDTO = BankAccountResponseDTO.builder()
                    .id(bankAccount.getId())
                    .type(bankAccount.getType())
                    .balance(bankAccount.getBalance())
                    .createAt(bankAccount.getCreateAt())
                    .currency(bankAccount.getCurrency())
                    .build();
            bankAccountResponseDTOS.add(bankAccountResponseDTO);



        });
        return  bankAccountResponseDTOS;
    }
    @Override
    public BankAccountResponseDTO bankAccount(String id){
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow();
        BankAccountResponseDTO bankAccountResponseDTO = BankAccountResponseDTO.builder()
                .id(bankAccount.getId())
                .type(bankAccount.getType())
                .balance(bankAccount.getBalance())
                .currency(bankAccount.getCurrency())
                .createAt(bankAccount.getCreateAt())
                .build();
        return bankAccountResponseDTO;
    }

    @Override
    public BankAccountResponseDTO update(String id, BankAccountRequestDTO bankAccountRequestDTO) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow();
        if(bankAccountRequestDTO.getType() != null) bankAccount.setType(bankAccountRequestDTO.getType());
        if (bankAccountRequestDTO.getBalance()!=null) bankAccount.setBalance(bankAccountRequestDTO.getBalance());
        if (bankAccountRequestDTO.getCurrency()!=null) bankAccount.setCurrency(bankAccountRequestDTO.getCurrency());
        BankAccount saveAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = BankAccountResponseDTO.builder()
                .type(saveAccount.getType())
                .balance(saveAccount.getBalance())
                .currency(saveAccount.getCurrency())
                .build();
        return bankAccountResponseDTO;

    }

    @Override
    public void delete(String id) {
        bankAccountRepository.deleteById(id);
    }
}
