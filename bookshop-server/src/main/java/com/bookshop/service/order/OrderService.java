package com.bookshop.service.order;


import com.bookshop.dto.client.ClientConfirmedOrderResponse;
import com.bookshop.dto.client.ClientOrderDetailResponse;
import com.bookshop.dto.client.ClientSimpleOrderRequest;
import com.bookshop.dto.client.ClientSimpleOrderResponse;

import java.util.List;

public interface OrderService {
    void cancelOrder(String code);

    ClientConfirmedOrderResponse createClientOrder(ClientSimpleOrderRequest request);

    List<ClientSimpleOrderResponse> get(Long userId);

    ClientOrderDetailResponse get(String code);
}
