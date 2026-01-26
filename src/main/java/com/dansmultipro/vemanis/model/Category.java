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

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
