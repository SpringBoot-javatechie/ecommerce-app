package com.javatechie.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatechie.dto.OrderResponseDTO;
import com.javatechie.entity.Order;
import com.javatechie.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService service;

    //Validation
    //logging
    //Exception handling
    @PostMapping
    public String placeNewOrder(@RequestBody Order order) {
        return service.placeAnOrder(order);
    }

    @GetMapping("/{orderId}")
    public OrderResponseDTO getOrder(@PathVariable String orderId) throws JsonProcessingException {
        log.info("OrderController::getOrder request fetch order by orderId {} ", orderId);
        OrderResponseDTO orderResponseDTO = service.getOrder(orderId);
        log.info("OrderController::getOrder Response  {} ", new ObjectMapper().writeValueAsString(orderResponseDTO));
        return orderResponseDTO;
    }


}
