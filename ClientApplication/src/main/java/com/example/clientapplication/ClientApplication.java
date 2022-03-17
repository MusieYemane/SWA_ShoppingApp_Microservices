package com.example.clientapplication;

import com.example.clientapplication.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

    @Autowired
    Client client;



    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(client.getProducts());

        Product product= new Product();
        product.setProductName("MacBook pro");
        product.setProductPrice(2000.0);
        product.setProductDescription("256 gb storage 16 RAM");
        product.setProductNumInStock(10);

        client.addProduct(product);
        System.out.println(client.getProducts());


        product.setProductName("Hp");
        product.setProductPrice(1200.0);
        product.setProductDescription("125 gb storage 16 RAM");
        product.setProductNumInStock(5);

        client.modifyProduct(product,product.getProductNumber());
        System.out.println(client.getProducts());


        System.out.println(client.getProducts());



    }


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }



}
