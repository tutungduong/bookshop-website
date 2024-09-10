package com.bookshop.service.order;


import com.bookshop.dto.order.OrderCancellationReasonRequest;
import com.bookshop.dto.order.OrderCancellationReasonResponse;
import com.bookshop.service.CrudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderCancellationReasonService implements CrudService<Long, OrderCancellationReasonRequest, OrderCancellationReasonResponse> {
    @Override
    public List<OrderCancellationReasonResponse> findAll() {
        return List.of();
    }

    @Override
    public OrderCancellationReasonResponse findById(Long aLong) {
        return null;
    }

    @Override
    public OrderCancellationReasonResponse save(OrderCancellationReasonRequest request) {
        return null;
    }

    @Override
    public OrderCancellationReasonResponse save(Long aLong, OrderCancellationReasonRequest request) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void delete(List<Long> longs) {

    }
}
