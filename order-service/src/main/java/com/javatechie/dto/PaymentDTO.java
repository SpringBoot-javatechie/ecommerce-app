package com.javatechie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDTO {

    private String payMode;
    private double amount;
    private Date paidDate;
    private String paymentStatus;
}
