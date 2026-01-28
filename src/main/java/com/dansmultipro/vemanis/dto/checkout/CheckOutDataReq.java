package com.dansmultipro.vemanis.dto.checkout;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CheckOutDataReq {

    @NotBlank(message = "Product Id Required")
    private String productId;

    @NotNull(message = "quantity Required")
    @Min(value = 1, message = "Quantity must greater than 0")
    private Integer quantity;

    public String getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
