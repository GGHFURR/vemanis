package com.dansmultipro.vemanis.service;

import com.dansmultipro.vemanis.exception.NotAllowedStateException;
import com.dansmultipro.vemanis.model.BaseModel;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public class BaseService {
    protected  <T extends BaseModel> void createBase (T entity){
        entity.setId(UUID.randomUUID());
        entity.setCreatedAt(LocalDateTime.now());
    }

    protected  <T extends BaseModel> void updateBase (T entity){
        entity.setUpdatedAt(LocalDateTime.now());
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
