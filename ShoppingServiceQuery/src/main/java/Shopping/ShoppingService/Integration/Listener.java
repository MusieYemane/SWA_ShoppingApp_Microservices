package Shopping.ShoppingService.Integration;

import Shopping.ShoppingService.Model.ShoppingCart;
import Shopping.ShoppingService.Service.ShoppingService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service

public class Listener {

    @Autowired
    private ShoppingService shoppingService;


    @KafkaListener(topics = {"shoppingCommand"})
    public void handleListener(@Payload String messageString){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
          Message message = objectMapper.readValue(messageString , Message.class);


            if(message.getCommand().equals("addCart")){
                String customerId = (String) message.getMessage();
                shoppingService.addShoppingCart(new ShoppingCart(customerId));
            }
             if(message.getCommand().equals("addProductAndQuantity")){
                 Message<CustomerProductQualityDTO> messageAddProductAndQuality = objectMapper.readValue(messageString  ,
                         new TypeReference<Message<CustomerProductQualityDTO>>() {}
                 );

                 CustomerProductQualityDTO customerProductQualityDTO1 = messageAddProductAndQuality.getMessage();
                shoppingService.addProductToAShoppingCart(
                        customerProductQualityDTO1.getCustomerId(),
                        customerProductQualityDTO1.getProduct(),
                        customerProductQualityDTO1.getQuantity());
            }
            else if(message.getCommand().equals("removeProductWithQuality")){
                 Message<CustomerProductQualityDTO> messageRemoveProductWithQuality = objectMapper.readValue(messageString  ,
                         new TypeReference<Message<CustomerProductQualityDTO>>() {}
                 );
                CustomerProductQualityDTO customerProductQualityDTO2 =
                        (CustomerProductQualityDTO) messageRemoveProductWithQuality.getMessage();

                shoppingService.removeProductWithQuantity(
                        customerProductQualityDTO2.getCustomerId(),
                        customerProductQualityDTO2.getProduct(),
                        customerProductQualityDTO2.getQuantity());

            }
            else if(message.getCommand().equals("removeAllProduct")){
                 Message<CustomerProductDTO> messageRemoveAllProduct = objectMapper.readValue(messageString  ,
                         new TypeReference<Message<CustomerProductDTO>>() {}
                 );

                CustomerProductDTO customerProductDTO =  messageRemoveAllProduct.getMessage();
                shoppingService.removeAllProduct(
                        customerProductDTO.getCustomerId(),
                        customerProductDTO.getProduct());
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
