package com.grammers.shop.product.presentation.dto;

import com.grammers.shop.product.application.dto.ProductCommand;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductRequest(
        String name,
        String sellerId,
        String description,
        BigDecimal price,
        Integer stock,
        String status,
        String operatorId
) {
    public ProductCommand toCommand(){
        UUID operator = operatorId != null ? UUID.fromString(operatorId) : null;
        UUID seller = sellerId != null ? UUID.fromString(sellerId) : null;
        return new ProductCommand(seller, name, description, price, stock, status, operator);
    }
}
