package tz.go.bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tz.go.bot.model.Customer;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    public List<Customer> findCustomersByAccountType(String accountType);
    public List<Customer> findCustomersByNameIsContaining(String contains);
    public List<Customer> findCustomersByAccountCreationDateAfter(LocalDate date);

}
