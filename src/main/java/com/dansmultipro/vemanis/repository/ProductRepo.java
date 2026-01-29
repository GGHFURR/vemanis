package com.dansmultipro.vemanis.repository;

import com.dansmultipro.vemanis.model.Category;
import com.dansmultipro.vemanis.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, UUID> {
    boolean existsByCode(String code);
    boolean existsByCategory(Category category);
    Optional<Product> findByCode(String code);
}
