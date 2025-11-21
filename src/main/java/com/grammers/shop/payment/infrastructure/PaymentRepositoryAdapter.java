package com.grammers.shop.payment.infrastructure;

import com.grammers.shop.payment.domain.Payment;
import com.grammers.shop.payment.domain.PaymentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PaymentRepositoryAdapter implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;

    public PaymentRepositoryAdapter(PaymentJpaRepository paymentJpaRepository) {
        this.paymentJpaRepository = paymentJpaRepository;
    }

    @Override
    public Page<Payment> findAll(Pageable pageable) {
        return paymentJpaRepository.findAll(pageable);
    }

    @Override
    public Optional<Payment> findById(UUID id) {
        return paymentJpaRepository.findById(id);
    }

    @Override
    public Payment save(Payment payment) {
        return paymentJpaRepository.save(payment);
    }
}