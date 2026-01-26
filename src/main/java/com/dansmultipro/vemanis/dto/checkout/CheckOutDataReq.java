package com.dansmultipro.vemanis.dto.checkout;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CheckOutDataReq {

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
