package com.example.customerservice.controller;


import com.example.customerservice.Service.CustomerService;
import com.example.customerservice.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @PostMapping("/save")
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }
    @PostMapping("/update")
    public Customer updateCustomer(@RequestBody Customer customer){
        return customerService.updateCustomer(customer);

    }
    @GetMapping("/delete{customerId}")
    public  void deleteCustomer(@RequestParam String customerId){

        customerService.deleteCustomer(customerId);

    }
    @GetMapping("/find{customerId}")
    public Optional<Customer> findById(@RequestParam  String customerId)
    {
        return customerService.findById(customerId);
    }
@GetMapping("/findall")
    public List<Customer> findAll(){
        return customerService.findAll();
    }
}
