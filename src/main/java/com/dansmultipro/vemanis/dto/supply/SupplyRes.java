package com.dansmultipro.vemanis.dto.supply;

import java.time.LocalDateTime;
import java.util.UUID;

public class SupplyRes {

    private UUID id;
    private String code;
    private LocalDateTime date;
    private String supplier;

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
