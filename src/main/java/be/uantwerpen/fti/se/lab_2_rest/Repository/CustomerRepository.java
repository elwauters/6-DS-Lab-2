package be.uantwerpen.fti.se.lab_2_rest.Repository;

import be.uantwerpen.fti.se.lab_2_rest.Model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findOneByEmail(String email);

}
