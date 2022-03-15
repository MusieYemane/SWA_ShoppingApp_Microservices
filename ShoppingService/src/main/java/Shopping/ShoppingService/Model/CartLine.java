package Shopping.ShoppingService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartLine {

    private Product product;

    private Integer quantity;

    public void changeQuantity(Integer quantity){
        this.quantity = quantity;
    }




}
