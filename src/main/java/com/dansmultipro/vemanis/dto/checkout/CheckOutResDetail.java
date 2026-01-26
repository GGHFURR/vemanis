package com.dansmultipro.vemanis.dto.checkout;

public class CheckOutResDetail {

    private String productName;
    private Integer quantity;

    public CheckOutResDetail(String productName, Integer quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
