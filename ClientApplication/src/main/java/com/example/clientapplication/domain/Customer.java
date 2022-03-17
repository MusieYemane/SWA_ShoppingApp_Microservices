package com.example.clientapplication.domain;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private String customerId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Address address;


}
