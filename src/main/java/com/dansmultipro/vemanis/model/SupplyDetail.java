package com.dansmultipro.vemanis.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_supply_detail")
public class SupplyDetail {

    @Column
    private LocalDateTime date;

    @Column
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn()
    private Supply supply;

}
