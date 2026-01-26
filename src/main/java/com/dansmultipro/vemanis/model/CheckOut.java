package com.dansmultipro.vemanis.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_checkout")

public class CheckOut extends BaseModel {

    @Column(unique = true, nullable = false, length = 5)
    private String code;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}
