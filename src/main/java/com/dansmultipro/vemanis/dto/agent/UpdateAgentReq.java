package com.dansmultipro.vemanis.dto.agent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateAgentReq {

    @NotBlank(message = "Name Required")
    @Size(min=1,max=55)
    private String name;

    @NotNull(message = "Please Refresh The Page")
    private Integer version;

    public String getName() {
        return name;
    }

    public Integer getVersion() {
        return version;
    }
}
