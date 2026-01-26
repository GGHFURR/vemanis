package com.dansmultipro.vemanis.dto.supplier;

import jakarta.validation.constraints.NotBlank;

public class CreateSupplierReq {

    @NotBlank(message = "Name Required")
    private String name;

    public String getName() {
        return name;
    }

}
