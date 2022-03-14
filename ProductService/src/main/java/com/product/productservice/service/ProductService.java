package com.product.productservice.service;

import com.product.productservice.domain.Product;
import com.product.productservice.domain.Products;

import java.util.List;

public interface ProductService {

    Products getAllProducts();
    Product getProductById(String productNumber);
    Product addProduct(Product product);
    void deleteProductById(String productNumber);
    Product editProduct(String productNumber, Product product);


}
