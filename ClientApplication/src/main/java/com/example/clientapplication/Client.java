package com.example.clientapplication;


import com.example.clientapplication.domain.Product;
import com.example.clientapplication.domain.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class Client implements ClientInterface{



    @Autowired
    private RestTemplate restTemplate;

    private final String addProductUrl = "http://localhost:8080/product";
    private final String modifyProductUrl = "http://localhost:8080/product";
    private final String getProductsUrl =  "http://localhost:8080/product";
    private final String addProductToShoppingCartUrl = "http://localhost:8080/cart/{customerId}";
    private final String showShoppingCartUrl = "http://localhost:8080/cartQuery/getShoppingCart/{customerId}";
    private final String removeProductFromShoppingCartUrl = "http://localhost:8080/cart";
    private final String checkoutShoppingCartUrl = "http://localhost:8080/cart/checkout/{customerId}";
    private final String placeAnOrderUrl = "http://localhost:8080/order/placeOrder/orderNumber/{orderNumber}";


    @Override
    public Product addProduct( Product product) {

        URI uri = restTemplate.postForLocation(addProductUrl, product);
        System.out.println(uri);

        return product;
    }

    @Override
    public Product modifyProduct(Product product,String productId ) {
        restTemplate.put(modifyProductUrl, product,productId);
        return product;
    }

    @Override
    public List<Product> getProducts() {
        ResponseEntity<List<Product>> response =
                restTemplate.exchange(getProductsUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Product>>() {});
        return response.getBody();
    }

    @Override
    public void addProductToShoppingCart(String customerId,Product product) {
        URI uri = restTemplate.postForLocation(addProductToShoppingCartUrl,product, customerId);
        System.out.println(uri);

    }

    @Override
    public void showShoppingCart(String customerId) {

        restTemplate.getForObject(showShoppingCartUrl, ShoppingCart.class, customerId);

    }

    @Override
    public void removeProductFromShoppingCart(String customerId, String productId) {
        restTemplate.delete(removeProductFromShoppingCartUrl, customerId , productId);
    }

    @Override
    public void checkoutShoppingCart(String customerId) {
        URI uri = restTemplate.postForLocation(checkoutShoppingCartUrl, customerId);
        System.out.println(uri);

    }

    @Override
    public void placeAnOrder(String orderNumber) {
        URI uri = restTemplate.postForLocation(placeAnOrderUrl, orderNumber);
        System.out.println(uri);


    }





}
