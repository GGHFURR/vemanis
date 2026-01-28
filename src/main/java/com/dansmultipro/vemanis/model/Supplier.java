package com.dansmultipro.vemanis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_supplier")
public class Supplier extends BaseModel {

    @Column(unique = true, length = 55)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
