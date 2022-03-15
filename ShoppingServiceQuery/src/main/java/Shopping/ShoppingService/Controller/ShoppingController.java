package Shopping.ShoppingService.Controller;

import Shopping.ShoppingService.Model.CartLines;
import Shopping.ShoppingService.Model.Product;
import Shopping.ShoppingService.Model.ShoppingCart;
import Shopping.ShoppingService.Service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    @GetMapping("/getShoppingCart/{customerId}")
    public CartLines getShoppingCart(@PathVariable String customerId){
        return shoppingService.viewShoppingCart(customerId);
    }

}
