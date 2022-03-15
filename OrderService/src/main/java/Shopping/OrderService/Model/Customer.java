package Shopping.OrderService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Address;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private String customerId;
    private String firstName;
    private String phone;
    private String email;
    private Address address;
}
