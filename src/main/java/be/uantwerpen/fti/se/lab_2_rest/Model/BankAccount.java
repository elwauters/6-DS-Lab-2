package be.uantwerpen.fti.se.lab_2_rest.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

import java.util.ArrayList;
import java.util.List;

@Entity
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = true, message = "Insufficient balance")
    private double balance;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    List<Customer> customers;

    public BankAccount() {
        this.balance = 0.0;
        this.customers = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}


