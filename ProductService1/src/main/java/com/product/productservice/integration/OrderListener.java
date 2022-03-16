package com.product.productservice.integration;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.productservice.domain.OrderLines;
import com.product.productservice.exception.ProductNotfoundException;
import com.product.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OrderListener {

    @Autowired
    private ProductService productService;

    @KafkaListener(topics= {"placeOrderTopic"})
    public void listenWhenOrderPlaced(@Payload String orderLinesString) {
        ObjectMapper mapper= new ObjectMapper();

        OrderLines orderLines=null;
        try {
            orderLines= mapper.readValue(orderLinesString, OrderLines.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        productService.removeQuantityOfProducts(orderLines);
    }
}
