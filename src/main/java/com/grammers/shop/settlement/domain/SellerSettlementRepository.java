package com.grammers.shop.settlement.domain;

import com.grammers.shop.seller.domain.Seller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SellerSettlementRepository {
    List<SellerSettlement> findByStatusAndSeller(SettlementStatus status, UUID sellerId);

    List<SellerSettlement> findByStatus(SettlementStatus status);

    void saveAll(List<SellerSettlement> sellerSettlements);
}
