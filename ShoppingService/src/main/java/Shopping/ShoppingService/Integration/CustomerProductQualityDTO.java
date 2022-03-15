package Shopping.ShoppingService.Integration;

import Shopping.ShoppingService.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerProductQualityDTO {


    private String customerId;

    private Product product;

    private Integer quantity;


}
