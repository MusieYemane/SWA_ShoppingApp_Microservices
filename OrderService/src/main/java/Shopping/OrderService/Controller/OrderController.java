package Shopping.OrderService.Controller;

import Shopping.OrderService.Model.CartLines;
import Shopping.OrderService.Model.Customer;
import Shopping.OrderService.Model.Order;
import Shopping.OrderService.Model.Orders;
import Shopping.OrderService.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/placeOrder/orderNumber/{orderNumber}")
    public void placeOrder(@PathVariable String orderNumber , @RequestBody Customer customer){

        orderService.placeOrder(orderNumber , customer);


    }

    @GetMapping("/getOrders")
    public ResponseEntity<?> getOrders(){
        return new ResponseEntity<Orders>(orderService.getOrders(),HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<?> createOrder(@RequestBody CartLines cartLines){
        Order order = orderService.createOrder(cartLines);

        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
}
