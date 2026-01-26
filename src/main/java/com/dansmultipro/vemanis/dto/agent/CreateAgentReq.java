package com.dansmultipro.vemanis.dto.agent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateAgentReq {

    @NotBlank(message = "Name Required")
    @Size(max = 55)
    private String name;

    public String getName() {
        return name;
    }
}
