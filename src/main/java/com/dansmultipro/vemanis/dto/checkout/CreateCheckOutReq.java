package com.dansmultipro.vemanis.dto.checkout;



import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public class CreateCheckOutReq {

    @NotBlank(message = "Agent Required")
    private String agentId;

    @NotNull(message = "Data Product and Quantity Required")
    private List<@Valid CheckOutDataReq> dataReqList;

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public void setDataReqList(List<CheckOutDataReq> dataReqList) {
        this.dataReqList = dataReqList;
    }

    public List<CheckOutDataReq> getDataReqList() {
        return dataReqList;
    }
}
