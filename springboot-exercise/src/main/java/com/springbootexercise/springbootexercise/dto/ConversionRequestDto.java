package com.springbootexercise.springbootexercise.dto;

import com.springbootexercise.springbootexercise.enumuration.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversionRequestDto {

    private UUID orderId;

    private double totalPrice;

    private UUID clientId;

    private TransactionType transactionType;

}
