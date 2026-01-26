package com.dansmultipro.vemanis.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_supply_detail")
public class SupplyDetail extends BaseModel {

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "supply_id")
    private Supply supply;

}
