package com.bookshop.service.order;


import com.bookshop.dto.ListResponse;
import com.bookshop.dto.client.ClientConfirmedOrderResponse;
import com.bookshop.dto.client.ClientOrderDetailResponse;
import com.bookshop.dto.client.ClientSimpleOrderRequest;
import com.bookshop.dto.client.ClientSimpleOrderResponse;

public interface ClientOrderService {
    void cancelOrder(String code);

    ListResponse<ClientSimpleOrderResponse> getAllOrders(String username, int page, int size, String sort, String filter);

    ClientConfirmedOrderResponse createClientOrder(ClientSimpleOrderRequest request);

    ClientOrderDetailResponse getCode(String code);

}
