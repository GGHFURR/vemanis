package com.dansmultipro.vemanis.dto.supply;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class CreateSupplyReq {
    @NotNull(message = "Date Required")
    private LocalDateTime date;

    @NotNull(message = "Supply Data Required")
    private List<SupplyDataReq> dataSupply;

    @NotBlank(message = "Supplier Required")
    private String supplier;

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setDataSupply(List<SupplyDataReq> dataSupply) {
        this.dataSupply = dataSupply;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
