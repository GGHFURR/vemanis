package com.dansmultipro.vemanis.dto.checkoutdetail;

import com.dansmultipro.vemanis.model.CheckOut;
import com.dansmultipro.vemanis.model.Product;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class CreateCheckOutDetailReq {

    @NotNull(message = "Quantity Required")
    private Integer quantity;

    @NotNull(message = "Product Required")
    private String product;

    public Integer getQuantity() {
        return quantity;
    }

    public String getProduct() {
        return product;
    }
}
