package com.finpulse.bankaccount_srevice;

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
    CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository){
        return args-> {
            Stream.of("Mohamed", "Yassine", "Hanae", "Inane").forEach(c -> {
                Customer customer = Customer.builder()
                        .name(c)
                        .build();
                Customer.save(customer);
            });
            Customer.findAll().forEach(c-> {
                for(int i=0; i<10 ;i++){
                    BankAccount bankAccount=BankAccount.builder()
                            .id(UUID.randomUUID().toString())
                            .type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
                            .balance(1000+Math.random()*90000)
                            .createAt(new Date())
                            .currency("MAD")
                            .customer(c)
                            .build();
                    bankAccount.save(bankAccount);
                }
            });

        };
    }
}
