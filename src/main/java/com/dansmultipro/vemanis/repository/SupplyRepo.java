package com.dansmultipro.vemanis.repository;

import com.dansmultipro.vemanis.model.Supplier;
import com.dansmultipro.vemanis.model.Supply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplyRepo extends JpaRepository<Supply, UUID> {
    boolean existBySupplier(Supplier supplier);
}
