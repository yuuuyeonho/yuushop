package com.grammers.shop.settlement.infrastructre;

import com.grammers.shop.seller.domain.Seller;
import com.grammers.shop.settlement.domain.SellerSettlement;
import com.grammers.shop.settlement.domain.SellerSettlementRepository;
import com.grammers.shop.settlement.domain.SettlementStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class SellerSettlementRepositoryAdapter implements SellerSettlementRepository {
    @Autowired
    private SellerSettlementJpaRepository jpaRepository;

    @Override
    public List<SellerSettlement> findByStatusAndSeller(SettlementStatus status, UUID sellerId){
        return jpaRepository.findByStatusAndSellerId(status, sellerId);
    }

    @Override
    public List<SellerSettlement> findByStatus(SettlementStatus status){
        return jpaRepository.findByStatus(status);
    }


    @Override
    public void saveAll(List<SellerSettlement> sellerSettlements){
        jpaRepository.saveAll(sellerSettlements);
    }
}
