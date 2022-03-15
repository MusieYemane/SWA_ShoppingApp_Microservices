package com.product.productservice.service.impl;

import com.product.productservice.domain.Product;
import com.product.productservice.domain.Products;
import com.product.productservice.repository.ProductRepository;
import com.product.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Products getAllProducts() {
        return new Products(productRepository.findAll());
    }

    @Override
    public Product getProductById(String productNumber) {
        return productRepository.findById(productNumber).orElse(null);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(String productNumber) {
        Product product= productRepository.findById(productNumber).orElse(null);
        if (product==null) return; // throw an exception (product not found)
        productRepository.deleteById(productNumber);
    }

    @Override
    public Product editProduct(String productNumber, Product product) {
        Product oldProduct= productRepository.findById(productNumber).orElse(null);
        if (oldProduct==null) return null; //throw an exception
        oldProduct.setProductName(product.getProductName());
        oldProduct.setProductPrice(product.getProductPrice());
        oldProduct.setProductDescription(product.getProductDescription());
        oldProduct.setProductNumInStock(product.getProductNumInStock());
        return productRepository.save(oldProduct);

    }
}
