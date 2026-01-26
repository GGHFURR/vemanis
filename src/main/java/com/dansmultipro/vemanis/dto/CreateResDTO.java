package com.dansmultipro.vemanis.dto;

import java.util.UUID;

public class CreateResDTO {
    private UUID id;
    private String message;

    public CreateResDTO(UUID id, String message) {
        this.id = id;
        this.message = message;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
