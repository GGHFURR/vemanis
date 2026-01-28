package com.dansmultipro.vemanis.dto.stockHistory;

import java.time.LocalDateTime;
import java.util.UUID;

public class StockHistoryRes {
    private UUID id;
    private LocalDateTime date;
    private Integer quantity;
    private String product;
    private String status;

    public StockHistoryRes(UUID id, LocalDateTime date, Integer quantity, String product, String status) {
        this.id = id;
        this.date = date;
        this.quantity = quantity;
        this.product = product;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

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

    public void setId(UUID id) {
        this.id = id;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
