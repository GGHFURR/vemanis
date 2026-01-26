package com.dansmultipro.vemanis.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_supply")
public class Supply extends BaseModel{
    @Column
    private String code;

    @Column
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn( name = "supplier_id")
    private Supplier supplier;

}
