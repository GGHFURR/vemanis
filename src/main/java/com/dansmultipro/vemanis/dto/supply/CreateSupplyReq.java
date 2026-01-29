package com.dansmultipro.vemanis.dto.supply;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CreateSupplyReq {

    @NotBlank(message = "Supplier Required")
    private String supplierId;

    @NotNull(message = "Supply Data Required")
    private List<@Valid SupplyDataReq> dataSupply;

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public List<SupplyDataReq> getDataSupply() {
        return dataSupply;
    }

    public void setDataSupply(List<SupplyDataReq> dataSupply) {
        this.dataSupply = dataSupply;
    }
}
