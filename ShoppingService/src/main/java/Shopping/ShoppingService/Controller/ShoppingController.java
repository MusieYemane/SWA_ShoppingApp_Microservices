package Shopping.ShoppingService.Controller;

import Shopping.ShoppingService.Model.Product;
import Shopping.ShoppingService.Model.ShoppingCart;
import Shopping.ShoppingService.Service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

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


}
