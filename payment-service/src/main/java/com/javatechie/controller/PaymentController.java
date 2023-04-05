package com.javatechie.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatechie.entity.Payment;
import com.javatechie.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService service;

    @GetMapping("/{orderId}")
    public Payment getPayment(@PathVariable String orderId) throws JsonProcessingException {
        log.info("PaymentController::getPayment request fetching payment by id  {} ", orderId);
        Payment paymentResponse = service.getByOrderId(orderId);
        log.info("PaymentController::getPayment fetching payment response  {} ", new ObjectMapper().writeValueAsString(paymentResponse));
        return paymentResponse;
    }
}
