package Shopping.OrderService.Integration;

import Shopping.OrderService.Model.CartLines;
import Shopping.OrderService.Service.OrderService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Listener {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = {"shoopingcheckout"})
    public void createOrder(CartLines cartLines){
        orderService.createOrder(cartLines);
    }
}
