package com.bookshop.config.payment.VNPAY;

import com.bookshop.dto.payment.OrderStatus;
import com.bookshop.dto.payment.VNPAY.VNPAYRequest;
import com.bookshop.dto.payment.VNPAY.VNPAYResponse;
import com.bookshop.utils.VNPayUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.jackson.EndpointObjectMapper;
import org.springframework.stereotype.Component;

import java.net.http.HttpClient;
import java.util.Map;

@AllArgsConstructor
@Component
@Slf4j
public class VNPAYHttpClient {

    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
    private final VNPAYConfig vnpayConfig;
    private final EndpointObjectMapper endpointObjectMapper;

    public VNPAYResponse createPaypalTransaction(VNPAYRequest vnpayRequest) throws Exception {

        long amount = Long.parseLong(vnpayRequest.getAmount()) * 100L;
        Map<String, String> vnpParamsMap = vnpayConfig.getVNPayConfig();
        vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
        vnpParamsMap.put("vnp_ReturnUrl", vnpayRequest.getApplicationContext().getReturnUrl());
        //        String bankCode = "";
//        if (bankCode != null && !bankCode.isEmpty()) {
//            vnpParamsMap.put("vnp_BankCode", bankCode);
//        }
        vnpParamsMap.put("vnp_IpAddr","172.0.0.1");
        //build query url
        String queryUrl = VNPayUtil.getPaymentURL(vnpParamsMap, true);
        String hashData = VNPayUtil.getPaymentURL(vnpParamsMap, false);
        String vnpSecureHash = VNPayUtil.hmacSHA512(vnpayConfig.getVnp_HashSecret(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String paymentUrl = vnpayConfig.getVnp_PayUrl() + "?" + queryUrl;

        VNPAYResponse vnpayResponse = new VNPAYResponse();
        vnpayResponse.setPaymentUrl(paymentUrl);
        vnpayResponse.setStatus(OrderStatus.CREATED);
        return vnpayResponse;
    }

}