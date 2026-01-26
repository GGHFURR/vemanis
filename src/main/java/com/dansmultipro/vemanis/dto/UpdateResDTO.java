package com.dansmultipro.vemanis.dto;

public class UpdateResDTO {
    private Integer version;
    private String message;

    public UpdateResDTO(Integer version, String message) {
        this.version = version;
        this.message = message;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
