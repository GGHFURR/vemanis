package com.dansmultipro.vemanis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_history_status")
public class HistoryStatus extends BaseModel{

    @Column(length = 5, nullable = false)
    private String code;
}
