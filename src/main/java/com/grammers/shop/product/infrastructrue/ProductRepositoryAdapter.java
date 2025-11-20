package com.grammers.shop.product.infrastructrue;

import com.grammers.shop.product.domain.Product;
import com.grammers.shop.product.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductRepositoryAdapter implements ProductRepository {
    @Autowired
    ProductJpaRepository productJpaRepository;

    @Override
    public Page<Product> findAll(Pageable pageable){return productJpaRepository.findAll(pageable);}

    @Override
    public Product save(Product product){return productJpaRepository.save(product);}

    @Override
    public Optional<Product> findById(UUID uuid){return productJpaRepository.findById(uuid);}

    @Override
    public void deleteById(UUID uuid){productJpaRepository.deleteById(uuid);}
}
