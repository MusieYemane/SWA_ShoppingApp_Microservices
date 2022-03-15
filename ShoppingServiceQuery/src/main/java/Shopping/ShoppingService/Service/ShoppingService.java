package Shopping.ShoppingService.Service;

import Shopping.ShoppingService.Model.CartLines;
import Shopping.ShoppingService.Model.Product;
import Shopping.ShoppingService.Model.ShoppingCart;
import Shopping.ShoppingService.Repository.ShoppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingService {

    @Autowired
    private ShoppingRepository shoppingRepository;

    public void addShoppingCart(ShoppingCart shoppingCart){
        shoppingRepository.save(shoppingCart);
    }

    public void addProductToAShoppingCart(String customerId, Product product,Integer quantity){
        ShoppingCart shoppingCart = shoppingRepository.findByCustomerId(customerId).get();

        shoppingCart.addProduct(product,quantity);
        shoppingRepository.save(shoppingCart);
    }

    public void removeProductWithQuantity(String customerId , Product product , Integer quantity){
        ShoppingCart shoppingCart = shoppingRepository.findByCustomerId(customerId).get();

        shoppingCart.removeProduct(product,quantity);
        shoppingRepository.save(shoppingCart);
    }

    public void removeAllProduct(String customerId , Product product ){
        ShoppingCart shoppingCart = shoppingRepository.findByCustomerId(customerId).get();

        shoppingCart.removeAllProduct(product);
        shoppingRepository.save(shoppingCart);
    }

    public CartLines viewShoppingCart(String customerId){

        ShoppingCart shoppingCart = shoppingRepository.findByCustomerId(customerId).get();

        return new CartLines(shoppingCart.viewShoopingcart());

    }

}
