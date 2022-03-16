package com.product.productservice.controller;


import com.product.productservice.domain.OrderLine;
import com.product.productservice.domain.OrderLines;
import com.product.productservice.domain.Product;
import com.product.productservice.domain.Products;
import com.product.productservice.exception.ProductNotfoundException;
import com.product.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final Logger logger =
            LoggerFactory.getLogger(ProductController.class.getName());

    @GetMapping
    public ResponseEntity<Products> getAllProducts(){
        logger.info("Calling Products");
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{productNumber}")
    public ResponseEntity<Product> getProductById(@PathVariable("productNumber") String productNumber){
        return ResponseEntity.ok(productService.getProductById(productNumber));
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @DeleteMapping("/{productNumber}")
    public void deleteProductById(@PathVariable("productNumber") String productNumber){
        productService.deleteProductById(productNumber);
    }

    @PutMapping("/{productNumber}")
    public ResponseEntity<Product> editProduct(@PathVariable String productNumber,
                                               @RequestBody Product product){
        return ResponseEntity.ok(productService.editProduct(productNumber, product));
    }

//    @GetMapping("/numInStock/{productNumber}")
//    public ResponseEntity<Integer> getProductNumInStock(@PathVariable String productNumber){
//
//        return ResponseEntity.ok(productService.getProductNumInStock(productNumber));
//    }
//    @PutMapping("/changeQuantity")
//    public void removeProductFromStock(@RequestBody OrderLines orderLines){
//        productService.removeQuantityOfProducts(orderLines);
//    }

}
