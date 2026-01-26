package com.dansmultipro.vemanis.dto.checkout;

import java.time.LocalDateTime;
import java.util.UUID;

public class CheckOutRes {
    private UUID id;
    private String code;
    private LocalDateTime date;
    private String agent;

    public CheckOutRes(UUID id, String code, LocalDateTime date, String agent) {
        this.id = id;
        this.code = code;
        this.date = date;
        this.agent = agent;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

}
