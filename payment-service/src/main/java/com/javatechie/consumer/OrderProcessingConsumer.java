package com.javatechie.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatechie.dto.Order;
import com.javatechie.dto.User;
import com.javatechie.entity.Payment;
import com.javatechie.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.function.Consumer;

@Component
public class OrderProcessingConsumer {

    public static final String USER_SERVICE_URL = "http://localhost:9090/users";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PaymentRepository repository;

    //@KafkaListener(topics = "ORDER_PAYMENT_TOPIC")
    @Bean
    public Consumer<String> processOrder() {
        return msg -> {
            try {
                Order order = new ObjectMapper()
                        .readValue(msg, Order.class);
                //build payment request
                Payment payment = Payment.builder()
                        .payMode(order.getPaymentMode())
                        .amount(order.getPrice())
                        .paidDate(new Date())
                        .userId(order.getUserId())
                        .orderId(order.getOrderId())
                        .build();
                if (payment.getPayMode().equals("COD")) {
                    payment.setPaymentStatus("PENDING");
                    //do rest call to user service (validate available amount) -> not required
                } else {
                    //validation
                    User user = restTemplate.getForObject(USER_SERVICE_URL + "/" + payment.getUserId(), User.class);
                    if (payment.getAmount() > user.getAvailableAmount()) {
                        throw new RuntimeException("Insufficient amount !");
                    } else {
                        payment.setPaymentStatus("PAID");
                        restTemplate.put(USER_SERVICE_URL + "/" + payment.getUserId() + "/" + payment.getAmount(), null);
                    }

                }
                repository.save(payment);
            } catch (JsonProcessingException e) {
                e.printStackTrace();//log
            }
        };
    }
}
