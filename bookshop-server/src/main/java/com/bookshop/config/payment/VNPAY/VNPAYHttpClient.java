package com.bookshop.config.payment.VNPAY;

import com.bookshop.dto.payment.VNPAY.VNPAYRequest;
import com.bookshop.dto.payment.VNPAY.VNPAYResponse;
import com.bookshop.utils.VNPayUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.http.HttpClient;
import java.util.Map;

@AllArgsConstructor
@Component
@Slf4j
public class VNPAYHttpClient {
//    private final VNPAYConfig vnPayConfig;
//     public VNPAYResponse createVnPayPayment(HttpServletRequest request) {
//
////         long amount = Integer.parseInt(request.getParameter("amount")) * 100L;
//        long amount = 1000000 * 100;
//        String bankCode = request.getParameter("bankCode");
//        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig();
//        vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
//        if (bankCode != null && !bankCode.isEmpty()) {
//            vnpParamsMap.put("vnp_BankCode", bankCode);
//        }
//        vnpParamsMap.put("vnp_IpAddr", VNPayUtil.getIpAddress(request));
//        //build query url
//        String queryUrl = VNPayUtil.getPaymentURL(vnpParamsMap, true);
//        String hashData = VNPayUtil.getPaymentURL(vnpParamsMap, false);
//        String vnpSecureHash = VNPayUtil.hmacSHA512(vnPayConfig.getVnp_HashSecret(), hashData);
//        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
//        String paymentUrl = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;
//
//         VNPAYResponse vnpayResponse = new VNPAYResponse();
//         vnpayResponse.setCode("ok");
//         vnpayResponse.setMessage("success");
//         vnpayResponse.setPaymentUrl(paymentUrl);
//
//         return vnpayResponse;
//    }

    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
    private final VNPAYConfig vnpayConfig;

    public VNPAYResponse createPaypalTransaction(VNPAYRequest vnpayRequest) throws Exception {

//        String bankCode = "";
        long amount = vnpayRequest.getAmount().longValue() * 100L;
        Map<String, String> vnpParamsMap = vnpayConfig.getVNPayConfig();
        vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
        vnpParamsMap.put("vnp_ReturnUrl", vnpayRequest.getApplicationContext().getReturnUrl());
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
        vnpayResponse.setCode("ok");
        vnpayResponse.setMessage("success");
        vnpayResponse.setPaymentUrl(paymentUrl);

        return vnpayResponse;
    }

}