package com.dansmultipro.vemanis.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateCategoryReq {

    @NotBlank(message = "Code Required")
    @Size(max = 5)
    private String code;

    @NotBlank(message = "Name Required")
    @Size(max =55)
    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
