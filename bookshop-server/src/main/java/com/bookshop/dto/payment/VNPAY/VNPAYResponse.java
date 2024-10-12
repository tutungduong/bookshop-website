package com.bookshop.dto.payment.VNPAY;


import com.bookshop.dto.payment.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class VNPAYResponse {
//    @JsonProperty("id")
//    private String id;
    @JsonProperty("status")
    private OrderStatus status;
    @JsonProperty("payment_url")
    private String paymentUrl;
}
