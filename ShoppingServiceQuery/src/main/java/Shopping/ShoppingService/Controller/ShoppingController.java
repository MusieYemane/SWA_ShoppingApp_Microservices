package Shopping.ShoppingService.Controller;

import Shopping.ShoppingService.Model.CartLines;
import Shopping.ShoppingService.Model.Product;
import Shopping.ShoppingService.Model.ShoppingCart;
import Shopping.ShoppingService.Service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartQuery")
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    @GetMapping("/getShoppingCart/{customerId}")
    public ResponseEntity<?> getShoppingCart(@PathVariable String customerId){

        CartLines cartLines = shoppingService.viewShoppingCart(customerId);

        return new ResponseEntity<CartLines>(cartLines,HttpStatus.OK);
    }



}
