package com.grammers.shop.payment.infrastructure;

import com.grammers.shop.payment.domain.PaymentFailure;
import com.grammers.shop.payment.domain.PaymentFailureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentFailureRepositoryAdapter implements PaymentFailureRepository {

    private final PaymentFailureJpaRepository jpaRepository;

    @Override
    public PaymentFailure save(PaymentFailure failure){return jpaRepository.save(failure);}
}
