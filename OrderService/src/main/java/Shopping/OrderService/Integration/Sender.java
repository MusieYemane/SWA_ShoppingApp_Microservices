package Shopping.OrderService.Integration;

import Shopping.OrderService.Service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(Message message){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String messageString = objectMapper.writeValueAsString(message);
            kafkaTemplate.send("placeOrderTopic" , messageString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
