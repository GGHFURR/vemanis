package com.dansmultipro.vemanis.repository;

import com.dansmultipro.vemanis.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierRepo extends JpaRepository<Supplier, UUID> {
}
