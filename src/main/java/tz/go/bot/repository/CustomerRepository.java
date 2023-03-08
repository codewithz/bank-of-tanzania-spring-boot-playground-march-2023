package tz.go.bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tz.go.bot.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
