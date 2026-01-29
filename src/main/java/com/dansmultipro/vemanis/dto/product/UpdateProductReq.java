package com.dansmultipro.vemanis.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateProductReq {

    @NotBlank(message = "Code Required")
    private String code;

    @NotBlank(message = "Name Required")
    private String name;

    @NotBlank(message = "Category Required")
    private String categoryId;

    @NotNull(message = "Please Refresh The Page")
    private Integer version;

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public Integer getVersion() {
        return version;
    }

}
