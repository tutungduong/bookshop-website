package com.bookshop.dto.payment.VNPAY;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class VNPAYResponse {
    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("paymentUrl")
    private String paymentUrl;
}
