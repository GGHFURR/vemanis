package com.dansmultipro.vemanis.dto.supply;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SupplyDataReq {

    @NotBlank(message = "Product Id Required")
    private String productId;

    @NotNull(message = "quantity Required")
    private Integer quantity;

    public String getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
