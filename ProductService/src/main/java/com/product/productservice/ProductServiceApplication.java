package com.product.productservice;

import com.product.productservice.domain.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        //create products
//        Product prod1= new Product();
//        prod1.setProductName("MacBooc pro");
//        prod1.setProductPrice(2000.0);
//        prod1.setProductDescription("%00 gb 16 RAM");
//        prod1.setProductNumInStock(10);
//        getRestTemplate().postForObject("http://localhost:8081/products", );
//        Product prod2= new Product();
//        prod1.setProductName("MacBooc pro");
//        prod1.setProductPrice(2000.0);
//        prod1.setProductDescription("%00 gb 16 RAM");
//        prod1.setProductNumInStock(10);

    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
