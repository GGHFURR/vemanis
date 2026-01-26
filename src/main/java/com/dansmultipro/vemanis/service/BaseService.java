package com.dansmultipro.vemanis.service;

import com.dansmultipro.vemanis.model.BaseModel;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public class BaseService {
    public <T extends BaseModel> T createBase (T entity){
        entity.setId(UUID.randomUUID());
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }

    public <T extends BaseModel> T updateBase (T entity){
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }
}
