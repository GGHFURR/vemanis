package com.dansmultipro.vemanis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "t_category"
)
public class Category extends BaseModel{
    @Column(length = 5, nullable = false)
    private String code;

    @Column(length = 55, nullable = false)
    private String name;


}
