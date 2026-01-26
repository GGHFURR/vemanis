package com.dansmultipro.vemanis.dto.agent;

import java.util.UUID;

public class AgentRes {
    private UUID id;
    private String name;

    public AgentRes(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
