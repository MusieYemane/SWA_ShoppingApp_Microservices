package com.example.customerservice.domain;

import com.example.customerservice.Service.CustomerService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Document
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
    @Autowired
    CustomerService customerService;
    @Id
    private String customerId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Address address;

//    public Customer updateCustomer(Customer customer){
//        return customerService.updateCustomer(customer);
//
//    }
//    public Optional<Customer> findById(@RequestParam String customerId)
//    {
//        return customerService.findById(customerId);
//    }

}
