package com.grammers.shop.payment.presentation.dto;

import com.grammers.shop.payment.application.dto.PaymentCommand;

public record PaymentRequest(
        String paymentKey,
        String orderId,
        Long amount
) {
    public PaymentCommand toCommand(){
        return new PaymentCommand(paymentKey, orderId, amount);
    }
}
