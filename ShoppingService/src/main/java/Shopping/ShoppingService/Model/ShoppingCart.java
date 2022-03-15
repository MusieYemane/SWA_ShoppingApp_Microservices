package Shopping.ShoppingService.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {

    @Id
    private String customerId;
    private List<CartLine> cartLineList = new ArrayList<>();

    public ShoppingCart(String customerId) {
        this.customerId = customerId;
    }

    public boolean addProduct(Product product, Integer quantity){
        for(CartLine cartLine  : cartLineList){
            if(cartLine.getProduct().equals(product)){
                cartLine.changeQuantity(cartLine.getQuantity() + quantity);
                return true;
            }
        }
        cartLineList.add(new CartLine(product,quantity));
        return true;
    }

    public boolean removeProduct(Product product,Integer quantity){
        for(CartLine cartLine : cartLineList){
            if(cartLine.getProduct().equals(product) && cartLine.getQuantity() > quantity ){
                cartLine.changeQuantity(cartLine.getQuantity() - quantity);
                return true;
            }
            else if(cartLine.getProduct().equals(product) && cartLine.getQuantity() < quantity ){
                return false;
            }
        }
        cartLineList.remove(product);
        return true;
    }

    public void removeAllProduct(Product product){
        cartLineList.remove(product);
    }

}