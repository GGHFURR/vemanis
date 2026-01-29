package com.dansmultipro.vemanis.dto.product;

import jakarta.validation.constraints.NotBlank;

public class CreateProductReq {

    @NotBlank(message = "Code Required")
    private String code;
    @NotBlank(message = "Name Required")
    private String name;
    @NotBlank(message = "Category Required")
    private String categoryId;

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getCategoryId() {
        return categoryId;
    }
}
