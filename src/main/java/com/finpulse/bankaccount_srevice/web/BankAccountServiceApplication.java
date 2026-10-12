package com.finpulse.bankaccount_srevice.web;

import com.finpulse.bankaccount_srevice.entities.BankAccount;
import com.finpulse.bankaccount_srevice.entities.Customer;
import com.finpulse.bankaccount_srevice.enums.AccountType;
import com.finpulse.bankaccount_srevice.repositories.BankAccountRepository;
import com.finpulse.bankaccount_srevice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository , CustomerRepository customerRepository){
        return args -> {
            Stream.of("Fatima","Maryam","Sanae").forEach(c->{
                Customer customer = Customer.builder()
                        .nom(c)
                        .build();
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(customer -> {
                for (int i=0 ;i<10 ; i++){
                    BankAccount bankAccount = BankAccount.builder()
                            .id(UUID.randomUUID().toString())
                            .type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
                            .balance(Math.random()*9000 + 1000)
                            .createAt(new Date())
                            .currency("MAD")
                            .customer(customer)
                            .build();
                    bankAccountRepository.save(bankAccount);


                }

            });


        };
    }

}

