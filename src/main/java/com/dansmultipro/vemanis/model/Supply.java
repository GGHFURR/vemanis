package com.dansmultipro.vemanis.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_supply")
public class Supply extends BaseModel{
    @Column(length = 5, unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn( name = "supplier_id")
    private Supplier supplier;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
