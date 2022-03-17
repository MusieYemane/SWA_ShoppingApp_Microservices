package com.example.clientapplication.domain;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String city;
    private String zip;
}
