package com.dansmultipro.vemanis.dto.supply;

import java.time.LocalDateTime;
import java.util.UUID;

public class SupplyRes {

    private UUID id;
    private String code;
    private LocalDateTime date;
    private String supplier;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
