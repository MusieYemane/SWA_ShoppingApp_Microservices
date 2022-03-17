package com.example.clientapplication;

import com.example.clientapplication.domain.*;
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

    private final String baseUrl = "http://localhost:8080";



    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("=============Get Products=============");
        System.out.println(client.getProducts());

        System.out.println("=============add Product: Macbook=============");
        Product product= new Product();
        product.setProductName("MacBook pro");
        product.setProductPrice(2000.0);
        product.setProductDescription("256 gb storage 16 RAM");
        product.setProductNumInStock(10);
//        client.addProduct(product);

        System.out.println("============= Get Products=============");
        var allProducts= client.getProducts();
        System.out.println(allProducts);

        //edit product
        System.out.println("============= Edit Product from Macbook to Hp=============");
        Product product1 = allProducts.getProducts().get(0);
        product1.setProductName("Hp");
        product1.setProductPrice(1200.0);
        product1.setProductDescription("125 gb storage 16 RAM");
        product1.setProductNumInStock(5);
//        client.modifyProduct(product1,product1.getProductNumber());
        System.out.println(client.getProducts());


        //create and get cutomers
        System.out.println("============= Create Customer ... =============");
        Customer cust1= new Customer();
        cust1.setFirstName("Michal");
        cust1.setLastName("Rezene");
        cust1.setEmail("Mike@gmail.com");
        cust1.setPhone("641256646");
        cust1.setAddress(new Address("1000 North st", "Fairfield", "52557"));
        restTemplate().postForLocation(baseUrl+"/customer/save", cust1, Customer.class);
        System.out.println("============= get Customer=============");
        System.out.println(restTemplate().getForObject(baseUrl+"/customer/findall", Customers.class));

        //Create shopping cart to a customer
        System.out.println("============= Create cart to Customer ...=============");
//        restTemplate().postForLocation(baseUrl+"/cart/addCartForACustomer/6232f2d4958c8e65d8362068", null, ShoppingCart.class);

        //Put product(Hp) to cart of customer(id=6232f2d4958c8e65d8362068 )
        System.out.println("============= Put product to cart=============");
//        restTemplate().postForLocation(baseUrl+"/cart/addProductToCartWithQuantity/6232f2d4958c8e65d8362068/quantity/3", product1, Products.class);


        // Show the shopping cart of customer (6232f2d4958c8e65d8362068)
        System.out.println("============= Get shopping cart=============");
        System.out.println(restTemplate().getForObject(baseUrl+"/cartQuery/getShoppingCart/6232f2d4958c8e65d8362068", ShoppingCart.class));



    }


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }



}
