package com.bookshop.config.payment.VNPAY;

import com.bookshop.dto.payment.VNPAY.VNPAYResponse;
import com.bookshop.utils.VNPayUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class VNPAYService {
    private final VNPAYConfig vnPayConfig;
    public VNPAYResponse createVnPayPayment(HttpServletRequest request) {
//        long amount = Integer.parseInt(request.getParameter("amount")) * 100L;
        long amount = 1000000 * 100;
        String bankCode = request.getParameter("bankCode");
        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig();
        vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
        if (bankCode != null && !bankCode.isEmpty()) {
            vnpParamsMap.put("vnp_BankCode", bankCode);
        }
        vnpParamsMap.put("vnp_IpAddr", VNPayUtil.getIpAddress(request));
        //build query url
        String queryUrl = VNPayUtil.getPaymentURL(vnpParamsMap, true);
        String hashData = VNPayUtil.getPaymentURL(vnpParamsMap, false);
        String vnpSecureHash = VNPayUtil.hmacSHA512(vnPayConfig.getVnp_HashSecret(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String paymentUrl = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;

         VNPAYResponse vnpayResponse = new VNPAYResponse();
         vnpayResponse.setCode("ok");
         vnpayResponse.setMessage("success");
         vnpayResponse.setPaymentUrl(paymentUrl);

         return vnpayResponse;
    }
}