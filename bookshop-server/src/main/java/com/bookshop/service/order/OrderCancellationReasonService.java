package com.bookshop.service.order;


import com.bookshop.constant.SearchFields;
import com.bookshop.dto.ListResponse;
import com.bookshop.dto.order.OrderCancellationReasonRequest;
import com.bookshop.dto.order.OrderCancellationReasonResponse;
import com.bookshop.entity.order.OrderCancellationReason;
import com.bookshop.repository.order.OrderCancellationReasonRepository;
import com.bookshop.service.CrudService;
import com.bookshop.utils.SearchUtils;
import io.github.perplexhub.rsql.RSQLJPASupport;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderCancellationReasonService implements CrudService<Long, OrderCancellationReasonRequest, OrderCancellationReasonResponse> {

    private final OrderCancellationReasonRepository orderCancellationReasonRepository;

     @Override
     public ListResponse<OrderCancellationReasonResponse> findAll(int page, int size, String sort, String filter, String search, boolean all) {
        Specification<OrderCancellationReason> sortable = RSQLJPASupport.toSort(sort);
        Specification<OrderCancellationReason> filterable = RSQLJPASupport.toSpecification(filter);
        Specification<OrderCancellationReason> searchable = SearchUtils.parse(search, SearchFields.ORDER_CANCELLATION_REASON);
        Pageable pageable = all ? Pageable.unpaged() : PageRequest.of(page - 1, size);
        Page<OrderCancellationReason> entities = orderCancellationReasonRepository.findAll(sortable.and(filterable).and(searchable), pageable);
        List<OrderCancellationReasonResponse> entityResponse = entities.getContent().stream()
            .map(this::entityToResponse)
            .collect(Collectors.toList());
     return new ListResponse<>(entityResponse, entities);
    }

    @Override
    public OrderCancellationReasonResponse findById(Long id) {
        return orderCancellationReasonRepository.findById(id)
                .map(this::entityToResponse)
                .orElse(null);
    }

    @Override
    public OrderCancellationReasonResponse save(OrderCancellationReasonRequest request) {
        OrderCancellationReason orderCancellationReason = requestToEntity(request);
        orderCancellationReason = orderCancellationReasonRepository.save(orderCancellationReason);
        return entityToResponse(orderCancellationReason);
    }

    @Override
    public OrderCancellationReasonResponse save(Long id, OrderCancellationReasonRequest request) {
        return orderCancellationReasonRepository.findById(id)
                .map(existingEntity -> paratialUpdate(existingEntity, request))
                .map(orderCancellationReasonRepository::save)
                .map(this::entityToResponse)
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

    private OrderCancellationReason requestToEntity(OrderCancellationReasonRequest request) {
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
        orderCancellationReason.setUpdatedAt(Instant.now());
        return orderCancellationReason;
    }

    private OrderCancellationReasonResponse entityToResponse(OrderCancellationReason orderCancellationReason) {
        OrderCancellationReasonResponse response = new OrderCancellationReasonResponse();
        response.setId(orderCancellationReason.getId());
        response.setName(orderCancellationReason.getName());
        response.setNote(orderCancellationReason.getNote());
        response.setStatus(orderCancellationReason.getStatus());
        response.setCreatedAt(orderCancellationReason.getCreatedAt());
        response.setUpdatedAt(orderCancellationReason.getUpdatedAt());
        return response;
    }
}
