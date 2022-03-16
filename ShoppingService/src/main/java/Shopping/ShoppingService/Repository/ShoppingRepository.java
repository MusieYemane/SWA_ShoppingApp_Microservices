package Shopping.ShoppingService.Repository;

import Shopping.ShoppingService.Model.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingRepository extends MongoRepository<ShoppingCart,String> {

    public Optional<ShoppingCart> findByCustomerId(String customerId);

}
