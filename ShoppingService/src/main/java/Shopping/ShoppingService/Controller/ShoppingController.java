package Shopping.ShoppingService.Controller;

import Shopping.ShoppingService.Model.CartLines;
import Shopping.ShoppingService.Model.Product;
import Shopping.ShoppingService.Service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    @Autowired
    private ShoppingFeignClient shoppingFeignClient;

    @PostMapping("/addCartForACustomer/{customerId}")
    public void addShoppingCartForCustomer(@PathVariable String customerId){
        shoppingService.addShoppingCart(customerId);
    }

    @PostMapping("/addProductToCartWithQuantity/{customerId}/quantity/{quantity}")
    public void addProductToShoppingCart(@PathVariable String customerId ,
            @PathVariable Integer quantity
            , @RequestBody Product product){
        shoppingService.addProductToAShoppingCart(customerId,product,quantity);
    }

    @PostMapping("/removeProductFromCartWithQuantity/{customerId}/quantity/{quantity}")
    public void removeProductWithQuantity(@PathVariable String customerId ,
                                         @PathVariable Integer quantity
            , @RequestBody Product product){
        shoppingService.removeProductWithQuantity(customerId,product,quantity);
    }

    @PostMapping("/removeProductFromCart/{customerId}")
    public void removeAllProduct(@PathVariable String customerId , @RequestBody Product product){
        shoppingService.removeAllProduct(customerId,product);
    }

    @PostMapping("/checkout/{customerId}")
    public void checkoutCart(@PathVariable String customerId){
        CartLines cartLines =  shoppingService.checkoutCart(customerId);
        shoppingFeignClient.createOrder(cartLines);
        System.out.println("Sending an order");

    }

    @FeignClient("OrderService")
    interface ShoppingFeignClient{

        @PostMapping
        void createOrder(@RequestBody CartLines cartLines);

    }





}
