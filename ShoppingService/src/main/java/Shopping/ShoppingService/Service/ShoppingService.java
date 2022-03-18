package Shopping.ShoppingService.Service;

import Shopping.ShoppingService.Integration.CustomerProductDTO;
import Shopping.ShoppingService.Integration.CustomerProductQualityDTO;
import Shopping.ShoppingService.Integration.Message;
import Shopping.ShoppingService.Integration.Sender;
import Shopping.ShoppingService.Model.CartLine;
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


    public ShoppingCart addShoppingCart(String customerId){
        Message<String> message = new Message<String>(
                "addCart",
                customerId
        );
       ShoppingCart shoppingCart = shoppingRepository.save(new ShoppingCart(customerId));

        sender.send(message);
        return shoppingCart;
    }

    public Product addProductToAShoppingCart(String customerId, Product product,Integer quantity){
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
        System.out.println(shoppingCart);
        shoppingRepository.save(shoppingCart);

        sender.send(customerProductQualityDTOMessage);
        return product;

    }

    public void removeProductWithQuantity(String customerId , String productId , Integer quantity){

        ShoppingCart shoppingCart = shoppingRepository.findByCustomerId(customerId).get();
        Product product = null;
        for(CartLine cartLine : shoppingCart.getCartLineList()){

            if(cartLine.getProduct().getProductNumber().equals(productId)){
                product = cartLine.getProduct();
                shoppingCart.removeProduct(cartLine.getProduct(),quantity);
            }

        }
        Message<CustomerProductQualityDTO> customerProductQualityDTOMessage =
                new Message<CustomerProductQualityDTO>(
                        "removeProductWithQuality",
                        new CustomerProductQualityDTO(
                                customerId,
                                product,
                                quantity)
                );

        shoppingRepository.save(shoppingCart);
        sender.send(customerProductQualityDTOMessage);

    }

    public void removeAllProduct(String customerId , String productId ){



        ShoppingCart shoppingCart = shoppingRepository.findByCustomerId(customerId).get();

        Product product = null;

        for(CartLine cartLine : shoppingCart.getCartLineList()){

            if(cartLine.getProduct().getProductNumber().equals(productId)){
                product = cartLine.getProduct();
                System.out.println();

                shoppingCart.getCartLineList().remove(cartLine);
                System.out.println("Shopping cart"+ shoppingCart);
                Message<CustomerProductDTO> customerProductQualityDTOMessage =
                        new Message<CustomerProductDTO>(
                                "removeAllProduct",
                                new CustomerProductDTO(
                                        customerId,
                                        product
                                )
                        );
                //System.out.println("Shopping cart"+ shoppingCart);
                shoppingRepository.save(shoppingCart);
                sender.send(customerProductQualityDTOMessage);
                return;

            }

        }



    }

    public CartLines checkoutCart(String customerId){

        Message<String> message = new Message<String>(
                "checkout",
                customerId
        );

        ShoppingCart cart = shoppingRepository.findByCustomerId(customerId).get();

        sender.send(message);
        System.out.println("This is the list of CartLines : "+cart.getCartLineList());

        return new CartLines(cart.getCartLineList());
    }

    public void removeCartLine(String customerId){
        ShoppingCart shoppingCart = shoppingRepository.findByCustomerId(customerId).get();
        shoppingCart.removeCartLineList();
        shoppingRepository.save(shoppingCart);
    }

}
