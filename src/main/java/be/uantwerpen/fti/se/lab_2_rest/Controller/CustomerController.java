package be.uantwerpen.fti.se.lab_2_rest.Controller;

import be.uantwerpen.fti.se.lab_2_rest.Model.DTO.BalanceUpdateRequest;
import be.uantwerpen.fti.se.lab_2_rest.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.logging.Logger;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    Logger logger = Logger.getLogger(CustomerController.class.getName());

    @GetMapping("/{email}/balance")
    public double getBalance(@PathVariable String email) throws ResponseStatusException {
        logger.info("GET request getBalance for customer " + email);
        return customerService.getBalanceFromCustomer(email);
    }

    @PutMapping("/{email}/balance/add")
    public double addBalance(@PathVariable String email, @RequestBody BalanceUpdateRequest balanceUpdateRequest) throws ResponseStatusException {
        logger.info("PUT request addBalance for customer " + email + " with amount " + balanceUpdateRequest.getAmount());
        return customerService.addBalanceToCustomer(email, balanceUpdateRequest.getAmount());
    }

    @PutMapping("/{email}/balance/remove")
    public double removeBalance(@PathVariable String email, @RequestBody BalanceUpdateRequest balanceUpdateRequest) throws ResponseStatusException {
        logger.info("PUT request removeBalance for customer " + email + " with amount " + balanceUpdateRequest.getAmount());
        return customerService.removeBalanceFromCustomer(email, balanceUpdateRequest.getAmount());
    }

    @DeleteMapping("/{email}/balance")
    public ResponseEntity<Void> deleteBalance(@PathVariable String email) throws ResponseStatusException {
        logger.info("DELETE request deleteBalance for customer " + email);
        customerService.deleteBalanceFromCustomer(email);
        return ResponseEntity.noContent().build();
    }
}

