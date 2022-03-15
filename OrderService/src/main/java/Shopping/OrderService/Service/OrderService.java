package Shopping.OrderService.Service;

import Shopping.OrderService.Model.*;
import Shopping.OrderService.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void createOrder(CartLines cartLines){
        Order order = new Order();
        List orderLineList = new ArrayList<>();
        for(CartLine cartLine : cartLines.getCartLineList()){
            OrderLine orderLine = new OrderLine();
            orderLine.setProduct(cartLine.getProduct());
            orderLine.setQuantity(cartLine.getQuantity());
            orderLineList.add(orderLine);
        }
        order.setOrderLineList(orderLineList);
    }

    public void placeOrder(String orderNumber , Customer customer){
        Order order = orderRepository.findById(orderNumber).get();
        order.setCustomer(customer);

        //productService gets Updated

        //CustomerService receive An Email
    }
}
