package Shopping.ShoppingService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String productNumber;
    private String productName;
    private Double productPrice;
    private String productDescription;
    private Integer productNumInStock;

}