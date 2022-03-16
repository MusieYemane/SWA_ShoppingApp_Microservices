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

    @Id
    private String customerId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Address address;


}
