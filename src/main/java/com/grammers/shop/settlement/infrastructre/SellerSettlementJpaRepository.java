package com.grammers.shop.settlement.infrastructre;

import com.grammers.shop.settlement.domain.SellerSettlement;
import com.grammers.shop.settlement.domain.SettlementStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SellerSettlementJpaRepository extends JpaRepository<SellerSettlement, UUID> {
    List<SellerSettlement> findByStatusAndSellerId(SettlementStatus status, UUID sellerId);

    List<SellerSettlement> findByStatus(SettlementStatus status);
}
