package com.dansmultipro.vemanis.repository;

import com.dansmultipro.vemanis.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AgentRepo extends JpaRepository<Agent, UUID> {
    boolean existsByName(String name);
    Optional<Agent> findByName(String name);
}
