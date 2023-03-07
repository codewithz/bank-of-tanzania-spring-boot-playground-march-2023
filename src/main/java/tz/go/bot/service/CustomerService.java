package tz.go.bot.service;

import org.springframework.stereotype.Service;
import tz.go.bot.model.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerService {

    List<Customer> customers=new ArrayList<>();

    {
        Customer customer1=
                new Customer(1,
                        "Tom","tom@gmail.com","Savings",
                        998877L, LocalDate.now());

        Customer customer2=new Customer(2,
                "Alex","alex@gmail.com","Savings",
                998855L, LocalDate.now());

        Customer customer3=new Customer(3,
                "Mike","mike@gmail.com","Current",
                998899L, LocalDate.now());

        customers.addAll(Arrays.asList(customer1,customer2,customer3));


    }

    public List<Customer> getAllCustomers(){
        System.out.println("--------- Customer Service Layer-------------");
        return customers;
    }

    public Customer getCustomerById(int id){
        Customer customer=null;

        for(Customer c:customers){
            if(c.getId()==id){
                customer=c;
                break;
            }
        }
        return customer;
    }

    public String addCustomer(Customer c){
        customers.add(c);
        return "SUCCESS";
    }

    public String updateCustomer(Customer updatedCustomer,int id){
        String message="Not Found";
        for(Customer c:customers){
            if(c.getId()==id){
                int index=customers.indexOf(c);
                customers.set(index,updatedCustomer);
                message="UPDATED";
                break;
            }
        }
        return message;

    }

    public String deleteCustomer(int id){
        String status="Not Deleted";
        for (Customer c:customers){
            if(c.getId()==id){
                customers.remove(c);
                status="Deleted";
                break;
            }
        }
        return  status;

    }


}
