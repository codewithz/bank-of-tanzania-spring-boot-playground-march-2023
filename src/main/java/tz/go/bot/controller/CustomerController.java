package tz.go.bot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tz.go.bot.model.Customer;
import tz.go.bot.service.CustomerService;

import java.util.List;

@RestController
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        List<Customer> customers=customerService.getAllCustomers();
        return  customers;
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable(name = "id") int id){
       Customer customer=customerService.getCustomerById(id);
       return  customer;
    }

    @PostMapping("/customers")
    public String addCustomer(@RequestBody  Customer customer){
       String status=customerService.addCustomer(customer);
       return status;
    }

    @PutMapping("/customers/{id}")
    public String updateCustomer(@RequestBody Customer customer,@PathVariable(name = "id") int id){
        String status=customerService.updateCustomer(customer,id);
        return  status;
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable(name = "id") int id){
        String status=customerService.deleteCustomer(id);
        return status;
    }
}
