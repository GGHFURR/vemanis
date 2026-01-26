package com.dansmultipro.vemanis.dto;

public class ErrorResDTO <T> {
    private T message;

    public ErrorResDTO(T message) {
        this.message = message;
    }

    public T getMessage() {
        return message;
    }
}
