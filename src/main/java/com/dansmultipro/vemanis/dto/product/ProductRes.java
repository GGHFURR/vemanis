package com.dansmultipro.vemanis.dto.product;


import java.util.UUID;

public class ProductRes {

    private UUID id;
    private String code;
    private String name;
    private String category;

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
