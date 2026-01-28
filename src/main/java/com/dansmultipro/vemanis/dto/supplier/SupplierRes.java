package com.dansmultipro.vemanis.dto.supplier;

import java.util.UUID;

public class SupplierRes {

    private UUID id;
    private String name;

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
