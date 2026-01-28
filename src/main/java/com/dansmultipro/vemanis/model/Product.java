package com.dansmultipro.vemanis.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "t_product"
)
public class Product extends BaseModel{

    @Column(length = 5, nullable = false, unique = true)
    private String code;

    @Column(length = 55, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
