package com.dansmultipro.vemanis.dto.stockHistory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CreateStockHistoryReq {
    @NotNull(message = "Date Required")
    private LocalDateTime date;

    @NotNull(message = "Quantity Required")
    private Integer quantity;

    @NotBlank(message = "Product Required")
    private String product;

    @NotBlank(message = "Status Required")
    private String status;

    public LocalDateTime getDate() {
        return date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getProduct() {
        return product;
    }

    public String getStatus() {
        return status;
    }
}
