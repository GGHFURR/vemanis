package com.dansmultipro.vemanis.dto.supply;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SupplyDataReq {

    @NotBlank(message = "Product Id Required")
    private String productId;

    @NotNull(message = "quantity Required")
    @Min(value = 1, message = "Quantity Must Greater Than 0")
    private Integer quantity;

    public String getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
