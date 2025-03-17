package be.uantwerpen.fti.se.lab_2_rest.Service;

import be.uantwerpen.fti.se.lab_2_rest.Model.BankAccount;
import be.uantwerpen.fti.se.lab_2_rest.Model.Customer;
import be.uantwerpen.fti.se.lab_2_rest.Repository.BankAccountRepository;
import be.uantwerpen.fti.se.lab_2_rest.Repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class DatabaseLoader {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    Logger logger = Logger.getLogger(DatabaseLoader.class.getName());


    public DatabaseLoader(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostConstruct
    public void initDatabase() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(0);
        Customer customer1 = new Customer();
        customer1.setEmail("user1@test.com");
        Customer customer2 = new Customer();
        customer2.setEmail("user2@test.com");
        customer1.setBankAccount(bankAccount);
        customer2.setBankAccount(bankAccount);
        bankAccountRepository.save(bankAccount);
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        logger.info("Database initialized");
    }
}
