package com.dansmultipro.vemanis.dto.supplier;

import jakarta.validation.constraints.NotBlank;

public class UpdateSupplierReq {

    @NotBlank(message = "Name Required")
    private String name;

    public String getName() {
        return name;
    }
}
