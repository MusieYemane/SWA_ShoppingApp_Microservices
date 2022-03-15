package Shopping.OrderService.Controller;

import Shopping.OrderService.Model.CartLines;
import Shopping.OrderService.Model.Customer;
import Shopping.OrderService.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/placeOrder/orderNumber/{orderNumber}")
    public void placeOrder(String orderNumber , Customer customer){
        orderService.placeOrder(orderNumber , customer);
    }
}
