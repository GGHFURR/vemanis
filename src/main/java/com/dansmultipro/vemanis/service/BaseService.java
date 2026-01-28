package com.dansmultipro.vemanis.service;

import com.dansmultipro.vemanis.exception.NotAllowedStateException;
import com.dansmultipro.vemanis.model.BaseModel;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public class BaseService {
    protected  <T extends BaseModel> T createBase (T entity){
        entity.setId(UUID.randomUUID());
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }

    protected  <T extends BaseModel> T updateBase (T entity){
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    protected UUID validateId(String id){
        if(id.isBlank()){
            throw new NotAllowedStateException("UUID Empty");
        }

        try{
            return UUID.fromString(id);
        }catch (IllegalArgumentException ex){
            throw new NotAllowedStateException("Invalid UUID");
        }

    }
}
