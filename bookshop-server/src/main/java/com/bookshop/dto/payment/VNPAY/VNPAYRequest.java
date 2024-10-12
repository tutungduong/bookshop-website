package com.bookshop.dto.payment.VNPAY;


import com.bookshop.dto.payment.OrderIntent;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
public class VNPAYRequest implements Serializable {
    @JsonProperty("intent")
    private OrderIntent intent;

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("application_context")
    private VNPAYAppContext applicationContext;

    @Data
    @Accessors(chain = true)
    public static class VNPAYAppContext{

        @JsonProperty("return_url")
        private String returnUrl;

        @JsonProperty("cancel_url")
        private String cancelUrl;
    }
}
