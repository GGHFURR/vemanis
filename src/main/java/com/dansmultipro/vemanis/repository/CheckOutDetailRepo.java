package com.dansmultipro.vemanis.repository;

import com.dansmultipro.vemanis.model.CheckOutDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CheckOutDetailRepo extends JpaRepository<CheckOutDetail, UUID> {

    Optional<CheckOutDetail> findByCheckOutId(UUID id);
}
