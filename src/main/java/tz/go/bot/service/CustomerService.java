package tz.go.bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tz.go.bot.model.Customer;
import tz.go.bot.repository.CustomerRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public List<Customer> getAllCustomers(){
       List<Customer> customers=customerRepository.findAll();
       if(!customers.isEmpty()){
           return customers;
       }
       else{
           throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"No customers found in database");
       }

    }

    public Customer getCustomerById(int id){
        Customer customer=null;
        Optional<Customer> optionalCustomer=customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
          customer=optionalCustomer.get();
            return customer;
        }
        else{
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Customer with id:"+id+" not found in database");
        }

    }

    public String addCustomer(Customer c){
       Customer addedCustomer=customerRepository.save(c);
       if(addedCustomer!=null){
           return "SUCCESS";
       }
       else{
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not created");
       }
    }

    public String updateCustomer(Customer updatedCustomer,int id){

        updatedCustomer.setId(id);
        Customer customer=customerRepository.save(updatedCustomer);
        if(customer!=null){
            return "SUCCESS";
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not updated");
        }


    }

    public String deleteCustomer(int id){
        String status="Not Deleted";

        Customer customerToBeDeleted=getCustomerById(id);
        if (customerToBeDeleted!=null){
            customerRepository.delete(customerToBeDeleted);
            status="Deleted";
            return status;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not deleted");
        }


    }


}
