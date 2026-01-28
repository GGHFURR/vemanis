package com.dansmultipro.vemanis.dto.supplier;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateSupplierReq {

    @NotBlank(message = "Name Required")
    private String name;

    @NotNull(message = "Please Refresh The Page")
    private Integer version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
