package be.uantwerpen.fti.se.lab_2_rest.Model;

import jakarta.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "bank_account_id") // Foreign key column
    private BankAccount bankAccount;

    public Customer() {

    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
