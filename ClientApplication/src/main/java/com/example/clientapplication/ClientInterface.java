package com.example.clientapplication;

import com.example.clientapplication.domain.Product;
import com.example.clientapplication.domain.Products;

import java.util.List;

public interface ClientInterface {

    public void addProduct(Product product);
    public Product modifyProduct(Product product ,String productId) ;
    public Products getProducts();
    public void addProductToShoppingCart(String customerId,Product product);
    public void showShoppingCart(String customerId);
    public void removeProductFromShoppingCart(String customerId,String product);
    public void checkoutShoppingCart(String customerId);
    public void placeAnOrder(String orderNumber);


}
