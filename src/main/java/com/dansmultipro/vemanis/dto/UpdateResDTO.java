package com.dansmultipro.vemanis.dto;

public class UpdateResDTO {
    private final Integer version;
    private final String message;

    public UpdateResDTO(Integer version, String message) {
        this.version = version;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Integer getVersion() {
        return version;
    }
}
