package Shopping.OrderService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
