package com.bookshop.dto.payment.VNPAY;


import lombok.Data;


@Data
public class VNPAYResponse {
//    @JsonProperty("id")
//    private String id;
//
//   @JsonProperty("status")
//    private OrderStatus status;
//    @JsonProperty("links")
//    private List<Link> links;
//
//     @Data
//    public static class Link {
//        @JsonProperty("href")
//        private String href;
//        @JsonProperty("rel")
//        private String rel;
//        @JsonProperty("method")
//        private String method;
//    }

    private String code;
    private String message;
    private String paymentUrl;
}
