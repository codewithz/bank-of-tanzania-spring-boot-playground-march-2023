package tz.go.bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tz.go.bot.model.Customer;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    public List<Customer> findCustomersByAccountType(String accountType);
    public List<Customer> findCustomersByNameIsContaining(String contains);
    public List<Customer> findCustomersByAccountCreationDateAfter(LocalDate date);

    @Query("Select c from Customer c where c.email=?1 and c.name=?2")
    public Optional<Customer> selectCustomerByEmail(String email,String name);

    @Query("Select c from Customer c where c.name like %:name% and c.accountType=:accountType")
    public List<Customer> selectCustomerWithNameLikeAndAccountType(
            @Param("name") String name,
            @Param("accountType") String accountType);



}
