package com.dansmultipro.vemanis.repository;

import com.dansmultipro.vemanis.model.Product;
import com.dansmultipro.vemanis.model.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StockHistoryRepo extends JpaRepository<StockHistory, UUID> {
    List<StockHistory> findByProduct(Product productId);
    boolean existsByProduct(Product product);
}
