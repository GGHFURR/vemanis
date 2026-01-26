package com.dansmultipro.vemanis.repository;

import com.dansmultipro.vemanis.model.HistoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HistoryStatusRepo extends JpaRepository<HistoryStatus, UUID> {
}
