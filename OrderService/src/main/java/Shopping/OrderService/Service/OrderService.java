package Shopping.OrderService.Service;

import Shopping.OrderService.Integration.Message;
import Shopping.OrderService.Integration.Sender;
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

    @Autowired
    private Sender sender;

    public Order createOrder(CartLines cartLines){

        Order order = new Order();
        List orderLineList = new ArrayList<>();
        for(CartLine cartLine : cartLines.getCartLineList()){
            System.out.println("Still talking");
            OrderLine orderLine = new OrderLine();
            orderLine.setProduct(cartLine.getProduct());
            orderLine.setQuantity(cartLine.getQuantity());
            orderLineList.add(orderLine);
        }
        order.setOrderLineList(orderLineList);
        Order order1 = orderRepository.save(order);
        return order1;
    }

    public void placeOrder(String orderNumber , Customer customer){
        Order order = orderRepository.findByOrderNumber(orderNumber).get();
        System.out.println("1"+order);
        order.setCustomer(customer);
        System.out.println("2"+order);

        OrderLines orderLines = new OrderLines(order.getOrderLineList());
        System.out.println("3"+order);

        Message<OrderLines> messageOrderLines = new Message<OrderLines>("productService" ,orderLines);

        Message<Order> messageOrder = new Message<Order>("customerService",order);
        System.out.println("1"+order);

        //productService gets Updated
        sender.send(messageOrderLines);

        System.out.println("This is the message to be send"+messageOrder);

        //CustomerService receive An Email
        sender.send(messageOrder);
    }
}
