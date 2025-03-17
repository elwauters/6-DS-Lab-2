package be.uantwerpen.fti.se.lab_2_rest.Service;

import be.uantwerpen.fti.se.lab_2_rest.Model.BankAccount;
import be.uantwerpen.fti.se.lab_2_rest.Model.Customer;
import be.uantwerpen.fti.se.lab_2_rest.Repository.BankAccountRepository;
import be.uantwerpen.fti.se.lab_2_rest.Repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Transactional
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public double getBalanceFromCustomer(String email) throws ResponseStatusException {
        BankAccount bankAccount = findCustomerByEmail(email).getBankAccount();
        return bankAccount.getBalance();
    }

    public double addBalanceToCustomer(String email, double balance)  {
        BankAccount bankAccount = findCustomerByEmail(email).getBankAccount();
        bankAccount.setBalance(bankAccount.getBalance() + balance);
        bankAccountRepository.save(bankAccount);
        return bankAccount.getBalance();
    }

    public double removeBalanceFromCustomer(String email, double balance) throws ResponseStatusException {
        BankAccount bankAccount = findCustomerByEmail(email).getBankAccount();
        if (bankAccount.getBalance() - balance < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient balance");
        } else {
            bankAccount.setBalance(bankAccount.getBalance() - balance);
            bankAccountRepository.save(bankAccount);
            return bankAccount.getBalance();
        }
    }

    public void deleteBalanceFromCustomer(String email) {
        BankAccount bankAccount = findCustomerByEmail(email).getBankAccount();
        bankAccount.setBalance(0);
        bankAccountRepository.save(bankAccount);
    }

    public Customer findCustomerByEmail(String email) throws ResponseStatusException {
        Optional<Customer> optionalCustomer = customerRepository.findOneByEmail(email);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with email " + email + " not found");
        }
    }
}
