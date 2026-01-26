package com.dansmultipro.vemanis.repository;

import com.dansmultipro.vemanis.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, UUID> {
}
