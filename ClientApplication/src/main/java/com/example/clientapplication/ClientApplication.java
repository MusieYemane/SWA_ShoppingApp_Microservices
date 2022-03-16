package com.example.clientapplication;

import com.example.clientapplication.domain.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //Add a number of products in the product service
        Product prod1= new Product();
        prod1.setProductName("MacBooc pro");
        prod1.setProductPrice(2000.0);
        prod1.setProductDescription("256 gb storage 16 RAM");
        prod1.setProductNumInStock(10);
//        restTemplate().postForObject("http://localhost:8081/products", );
        Product prod2= new Product();
        prod1.setProductName("MacBooc pro");
        prod1.setProductPrice(2000.0);
        prod1.setProductDescription("500 gb 16 RAM");
        prod1.setProductNumInStock(10);
    }


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }



}
