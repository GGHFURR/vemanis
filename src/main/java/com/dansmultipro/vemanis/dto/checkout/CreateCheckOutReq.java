package com.dansmultipro.vemanis.dto.checkout;



import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public class CreateCheckOutReq {

    @NotBlank(message = "Code Required")
    @Size(max = 5)
    private String code;

    @NotNull(message = "Date Required ")
    private LocalDateTime date;

    @NotBlank(message = "Agent Required")
    private String agent;

    @NotNull(message = "Data Product and Quantity Required")
    private List<@Valid CheckOutDataReq> dataReqList;

    public String getCode() {
        return code;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getAgent() {
        return agent;
    }

    public List<CheckOutDataReq> getDataReqList() {
        return dataReqList;
    }
}
