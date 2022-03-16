package Shopping.ShoppingService.Service;

import Shopping.ShoppingService.Integration.CustomerProductDTO;
import Shopping.ShoppingService.Integration.CustomerProductQualityDTO;
import Shopping.ShoppingService.Integration.Message;
import Shopping.ShoppingService.Integration.Sender;
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

    @Autowired
    private Sender sender;


    public void addShoppingCart(String customerId){
        Message<String> message = new Message<String>(
                "addCart",
                customerId
        );
        shoppingRepository.save(new ShoppingCart(customerId));

        sender.send(message);
    }

    public void addProductToAShoppingCart(String customerId, Product product,Integer quantity){
        Message<CustomerProductQualityDTO> customerProductQualityDTOMessage =
                new Message<CustomerProductQualityDTO>(
                        "addProductAndQuantity",
                        new CustomerProductQualityDTO(
                        customerId,
                        product,
                        quantity)
                );
        ShoppingCart shoppingCart = shoppingRepository.findByCustomerId(customerId).get();

        shoppingCart.addProduct(product,quantity);
        shoppingRepository.save(shoppingCart);

        sender.send(customerProductQualityDTOMessage);
    }

    public void removeProductWithQuantity(String customerId , Product product , Integer quantity){
        Message<CustomerProductQualityDTO> customerProductQualityDTOMessage =
                new Message<CustomerProductQualityDTO>(
                        "removeProductWithQuality",
                        new CustomerProductQualityDTO(
                                customerId,
                                product,
                                quantity)
                );
        ShoppingCart shoppingCart = shoppingRepository.findByCustomerId(customerId).get();

        shoppingCart.removeProduct(product,quantity);
        shoppingRepository.save(shoppingCart);
        sender.send(customerProductQualityDTOMessage);

    }

    public void removeAllProduct(String customerId , Product product ){
        Message<CustomerProductDTO> customerProductQualityDTOMessage =
                new Message<CustomerProductDTO>(
                        "removeAllProduct",
                        new CustomerProductDTO(
                                customerId,
                                product
                                )
                );
        ShoppingCart shoppingCart = shoppingRepository.findByCustomerId(customerId).get();

        shoppingCart.removeAllProduct(product);
        shoppingRepository.save(shoppingCart);
        sender.send(customerProductQualityDTOMessage);

    }

    public CartLines checkoutCart(String customerId){

        ShoppingCart cart = shoppingRepository.findByCustomerId(customerId).get();

        return new CartLines(cart.getCartLineList());
    }

}
