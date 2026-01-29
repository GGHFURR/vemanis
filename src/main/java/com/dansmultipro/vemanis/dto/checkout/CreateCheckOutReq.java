package com.dansmultipro.vemanis.dto.checkout;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CreateCheckOutReq {

    @NotBlank(message = "Agent Required")
    private String agentId;

    @NotNull(message = "Data Product and Quantity Required")
    private List<@Valid CheckOutDataReq> dataReqList;

    public String getAgentId() {
        return agentId;
    }

    public List<CheckOutDataReq> getDataReqList() {
        return dataReqList;
    }
}
