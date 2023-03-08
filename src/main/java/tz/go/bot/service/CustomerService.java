package tz.go.bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

//    List<Customer> customers=new ArrayList<>();
//
//    {
//        Customer customer1=
//                new Customer(1,
//                        "Tom","tom@gmail.com","Savings",
//                        998877L, LocalDate.now());
//
//        Customer customer2=new Customer(2,
//                "Alex","alex@gmail.com","Savings",
//                998855L, LocalDate.now());
//
//        Customer customer3=new Customer(3,
//                "Mike","mike@gmail.com","Current",
//                998899L, LocalDate.now());
//
//        customers.addAll(Arrays.asList(customer1,customer2,customer3));
//
//
//    }

    public List<Customer> getAllCustomers(){
       List<Customer> customers=customerRepository.findAll();
       return customers;
    }

    public Customer getCustomerById(int id){
        Customer customer=null;
        Optional<Customer> optionalCustomer=customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
          customer=optionalCustomer.get();
        }
        return customer;
    }

    public String addCustomer(Customer c){
       Customer addedCustomer=customerRepository.save(c);
       if(addedCustomer!=null){
           return "SUCCESS";
       }
       else{
           return "FAILURE";
       }
    }

    public String updateCustomer(Customer updatedCustomer,int id){

        updatedCustomer.setId(id);
        Customer customer=customerRepository.save(updatedCustomer);
        if(customer!=null){
            return "SUCCESS";
        }
        else{
            return "FAILURE";
        }


    }

    public String deleteCustomer(int id){
        String status="Not Deleted";

        Customer customerToBeDeleted=getCustomerById(id);
        if (customerToBeDeleted!=null){
            customerRepository.delete(customerToBeDeleted);
            status="Deleted";
        }
        else{
            status="Not Found";
        }
        return status;

    }


}
