package com.dansmultipro.vemanis.repository;

import com.dansmultipro.vemanis.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepo extends JpaRepository<Category, UUID> {
    boolean existsByName(String name);
    boolean existsByCode(String code);
    Optional<Category> findByName(String name);
}
