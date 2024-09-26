package com.bookshop.dto.payment.VNPAY;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class VNPAYRequest implements Serializable {
    //    @JsonProperty("intent")
//    private OrderIntent intent;
//
//    @JsonProperty("purchase_units")
//    private List<PurchaseUnit> purchaseUnits;
//
//    @JsonProperty("application_context")
//    private PayPalAppContext applicationContext;
//
//    @Data
//    @AllArgsConstructor
//    public static class PurchaseUnit {
//        @JsonProperty("amount")
//        private BigDecimal amount;
//
//    }
//
//    @Data
//    @Accessors(chain = true)
//    public static class PayPalAppContext {
//
//        @JsonProperty("bank_code")
//        private String bankCode;
//
//        @JsonProperty("return_url")
//        private String returnUrl;
//
//        @JsonProperty("cancel_url")
//        private String cancelUrl;
//    }
    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("application_context")
    private PayPalAppContext applicationContext;

    @Data
    @Accessors(chain = true)
    public static class PayPalAppContext {
//
//        @JsonProperty("bank_code")
//        private String bankCode;

        @JsonProperty("return_url")
        private String returnUrl;

        @JsonProperty("cancel_url")
        private String cancelUrl;
    }
}
