package be.uantwerpen.fti.se.lab_2_rest.Repository;

import be.uantwerpen.fti.se.lab_2_rest.Model.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {

}
