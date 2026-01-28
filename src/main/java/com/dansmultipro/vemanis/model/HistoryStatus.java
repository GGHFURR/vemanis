package com.dansmultipro.vemanis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_history_status")
public class HistoryStatus extends BaseModel{

    @Column(length = 5, nullable = false, unique = true)
    private String code;

    @Column(length = 55, nullable = false)
    private String name;

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
}
