package com.bookshop.service.order;


import com.bookshop.dto.order.OrderCancellationReasonRequest;
import com.bookshop.dto.order.OrderCancellationReasonResponse;
import com.bookshop.entity.order.OrderCancellationReason;
import com.bookshop.repository.order.OrderCancellationReasonRepository;
import com.bookshop.service.CrudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderCancellationReasonService implements CrudService<Long, OrderCancellationReasonRequest, OrderCancellationReasonResponse> {

    private final OrderCancellationReasonRepository orderCancellationReasonRepository;

    @Override
    public List<OrderCancellationReasonResponse> findAll() {
        return orderCancellationReasonRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderCancellationReasonResponse findById(Long id) {
        return orderCancellationReasonRepository.findById(id)
                .map(this::mapToResponse)
                .orElse(null);
    }

    @Override
    public OrderCancellationReasonResponse save(OrderCancellationReasonRequest request) {
        OrderCancellationReason orderCancellationReason = mapToEntity(request);
        orderCancellationReason = orderCancellationReasonRepository.save(orderCancellationReason);
        return mapToResponse(orderCancellationReason);
    }

    @Override
    public OrderCancellationReasonResponse save(Long id, OrderCancellationReasonRequest request) {
        return orderCancellationReasonRepository.findById(id)
                .map(existingEntity -> paratialUpdate(existingEntity, request))
                .map(orderCancellationReasonRepository::save)
                .map(this::mapToResponse)
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        orderCancellationReasonRepository.deleteById(id);
    }

    @Override
    public void delete(List<Long> ids) {
        orderCancellationReasonRepository.deleteAllById(ids);
    }

    private OrderCancellationReason mapToEntity(OrderCancellationReasonRequest request) {
        OrderCancellationReason orderCancellationReason = new OrderCancellationReason();
        orderCancellationReason.setName(request.getName());
        orderCancellationReason.setNote(request.getNote());
        orderCancellationReason.setStatus(request.getStatus());
        return orderCancellationReason;
    }

    private OrderCancellationReason paratialUpdate(OrderCancellationReason orderCancellationReason, OrderCancellationReasonRequest request) {
        orderCancellationReason.setStatus(request.getStatus());
        orderCancellationReason.setNote(request.getNote());
        orderCancellationReason.setName(request.getName());
        return orderCancellationReason;
    }

    private OrderCancellationReasonResponse mapToResponse(OrderCancellationReason orderCancellationReason) {
        OrderCancellationReasonResponse response = new OrderCancellationReasonResponse();
        response.setId(orderCancellationReason.getId());
        response.setName(orderCancellationReason.getName());
        response.setNote(orderCancellationReason.getNote());
        response.setStatus(orderCancellationReason.getStatus());
        return response;
    }
}
