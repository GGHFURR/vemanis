package com.dansmultipro.vemanis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Table(
        name = "t_product"
)
public class Product extends BaseModel{

    @Column(length = 5, nullable = false)
    private String code;

    @Column(length = 55, nullable = false)
    private String name;

    @Column
    private Category category;

}
