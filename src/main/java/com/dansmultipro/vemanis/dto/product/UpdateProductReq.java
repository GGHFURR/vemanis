package com.dansmultipro.vemanis.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateProductReq {

    @NotBlank(message = "Code Required")
    private String code;

    @NotBlank(message = "Name Required")
    private String name;

    @NotBlank(message = "Category Required")
    private String category;

    @NotNull(message = "Please Refresh The Page")
    private Integer version;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Integer getVersion() {
        return version;
    }
}
