package Shopping.OrderService.Controller;

import Shopping.OrderService.Model.CartLines;
import Shopping.OrderService.Model.Customer;
import Shopping.OrderService.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/placeOrder/orderNumber/{orderNumber}")
    public void placeOrder(@PathVariable String orderNumber , @RequestBody Customer customer){
        orderService.placeOrder(orderNumber , customer);
    }

    @PostMapping()
    public void createOrder(@RequestBody CartLines cartLines){
        orderService.createOrder(cartLines);
    }
}
