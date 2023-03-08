package tz.go.bot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tz.go.bot.model.Customer;
import tz.go.bot.payload.ApiSuccessPayload;
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
    public ResponseEntity<ApiSuccessPayload> getAllCustomers(){
        List<Customer> customers=customerService.getAllCustomers();
        HttpStatus status=HttpStatus.OK;
        ApiSuccessPayload payload=ApiSuccessPayload.build(customers,status,"Customers List Found");
        ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<>(payload,status);
        return  response;
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<ApiSuccessPayload> getCustomerById(@PathVariable(name = "id") int id){
       Customer customer=customerService.getCustomerById(id);
       HttpStatus status=HttpStatus.OK;
       ApiSuccessPayload payload=ApiSuccessPayload.build(customer,status,"Customer Found");
       ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<>(payload,status);
       return response;
    }

    @PostMapping("/customers")
    public ResponseEntity<ApiSuccessPayload> addCustomer(@RequestBody  Customer customer){
       String message=customerService.addCustomer(customer);
       HttpStatus status=HttpStatus.CREATED;
       ApiSuccessPayload payload=ApiSuccessPayload.build(message,status,message);
        ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<>(payload,status);
        return response;
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<ApiSuccessPayload> updateCustomer(@RequestBody Customer customer,@PathVariable(name = "id") int id){
        String message=customerService.updateCustomer(customer,id);
        HttpStatus status=HttpStatus.NO_CONTENT;
        ApiSuccessPayload payload=ApiSuccessPayload.build(message,status,message);
        ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<>(payload,status);
        return response;
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<ApiSuccessPayload> deleteCustomer(@PathVariable(name = "id") int id){
        String message=customerService.deleteCustomer(id);
        HttpStatus status=HttpStatus.NO_CONTENT;
        ApiSuccessPayload payload=ApiSuccessPayload.build(message,status,message);
        ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<>(payload,status);
        return response;
    }
}
