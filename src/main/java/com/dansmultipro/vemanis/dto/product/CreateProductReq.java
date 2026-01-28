package com.dansmultipro.vemanis.dto.product;

import com.dansmultipro.vemanis.model.Category;
import jakarta.validation.constraints.NotBlank;

public class CreateProductReq {

    @NotBlank(message = "Code Required")
    private String code;
    @NotBlank(message = "Name Required")
    private String name;
    @NotBlank(message = "Category Required")
    private String categoryId;

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
