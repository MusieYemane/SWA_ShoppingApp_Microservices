package Shopping.ShoppingService.Controller;

import Shopping.ShoppingService.Model.CartLines;
import Shopping.ShoppingService.Model.Product;
import Shopping.ShoppingService.Model.ProductOutOfStockError;
import Shopping.ShoppingService.Model.ShoppingCart;
import Shopping.ShoppingService.Service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    @Autowired
    private ShoppingFeignClient shoppingFeignClient;

    @Autowired
    private ProductFeignClient productFeignClient;

    @PostMapping("/addCartForACustomer/{customerId}")
    public ResponseEntity<?> addShoppingCartForCustomer(@PathVariable String customerId){

        ShoppingCart shoppingCart =  shoppingService.addShoppingCart(customerId);

        return new ResponseEntity<ShoppingCart>(shoppingCart,HttpStatus.OK);
    }

    @PostMapping("/addProductToCartWithQuantity/{customerId}/quantity/{quantity}")
    public ResponseEntity<?> addProductToShoppingCart(@PathVariable String customerId ,
            @PathVariable Integer quantity
            , @RequestBody Product product){
        if(productFeignClient.checkProductInStock(product.getProductNumber())){
            Product product1 = shoppingService.addProductToAShoppingCart(customerId,product,quantity);
            return new ResponseEntity<Product>(product1,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<ProductOutOfStockError>(
                    new ProductOutOfStockError("Product with this "+
                            product +" doesn't exist or out of stock"),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/removeProductFromCartWithQuantity/{customerId}/quantity/{quantity}")
    public ResponseEntity<?> removeProductWithQuantity(@PathVariable String customerId ,
                                         @PathVariable Integer quantity
            , @RequestBody Product product){
        shoppingService.removeProductWithQuantity(customerId,product,quantity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/removeProductFromCart/{customerId}")
    public ResponseEntity<?> removeAllProduct(@PathVariable String customerId , @RequestBody Product product){

        shoppingService.removeAllProduct(customerId,product);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/checkout/{customerId}")
    public ResponseEntity<?> checkoutCart(@PathVariable String customerId){
        CartLines cartLines =  shoppingService.checkoutCart(customerId);
        shoppingFeignClient.createOrder(cartLines);
        System.out.println("Sending an order");
        shoppingService.removeCartLine(customerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @FeignClient("OrderService")
    interface ShoppingFeignClient{

        @PostMapping
        void createOrder(@RequestBody CartLines cartLines);

    }


    @FeignClient("ProductService")
    interface ProductFeignClient{

        @GetMapping("/products/{productNumber}/isInStock")
        boolean checkProductInStock(@PathVariable("productNumber") String productNumber);

    }





}
