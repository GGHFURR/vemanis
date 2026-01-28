package com.dansmultipro.vemanis.repository;

import com.dansmultipro.vemanis.model.SupplyDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SupplyDetailRepo extends JpaRepository<SupplyDetail, UUID> {
    Optional<SupplyDetail> findBySupplyId(UUID id);
}
