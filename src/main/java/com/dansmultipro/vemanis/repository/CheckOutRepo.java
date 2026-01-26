package com.dansmultipro.vemanis.repository;

import com.dansmultipro.vemanis.model.CheckOut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CheckOutRepo extends JpaRepository<CheckOut, UUID> {
}
