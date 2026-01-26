package com.dansmultipro.vemanis.dto.supplyDetail;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public class CreateSupplyDetailReq {
    @NotNull(message = "Date Required")
    private LocalDateTime date;

    @NotNull(message = "Quantity Required")
    private Integer quantity;

    @NotNull(message = "Product Required")
    private String product;

    @NotBlank(message = "Supply Required")
    private String supply;

    public LocalDateTime getDate() {
        return date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getProduct() {
        return product;
    }

    public String getSupply() {
        return supply;
    }
}
