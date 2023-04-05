package com.javatechie.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EcomGatewayConfig {

//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//
//        return builder.routes()
//                .route("order-service", r -> r.path("/orders/**")
//                        .and()
//                        .uri("lb://ORDER-SERVICE"))
//
//                .route("payment-service", r -> r.path("/payments/**")
//                        .and()
//                        .uri("lb://PAYMENT-SERVICE"))
//
//                .route("user-service", r -> r.path("/users/**")
//                        .and()
//                        .uri("lb://USER-SERVICE"))
//
//                .build();
//
//    }
}
