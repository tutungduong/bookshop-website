package com.bookshop.service.order;

import com.bookshop.dto.order.OrderRequest;
import com.bookshop.dto.order.OrderResponse;
import com.bookshop.dto.order.OrderVariantRequest;
import com.bookshop.entity.authentication.User;
import com.bookshop.entity.order.Order;
import com.bookshop.entity.order.OrderCancellationReason;
import com.bookshop.entity.order.OrderVariant;
import com.bookshop.entity.product.Variant;
import com.bookshop.repository.authentication.UserRepository;
import com.bookshop.repository.order.OrderCancellationReasonRepository;
import com.bookshop.repository.order.OrderRepository;
import com.bookshop.repository.product.VariantRepository;
import com.bookshop.service.CrudService;
import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;
import com.bookshop.dto.authentication.UserResponse;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderService implements CrudService<Long, OrderRequest, OrderResponse> {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderCancellationReasonRepository orderCancellationReasonRepository;
    private final VariantRepository variantRepository;

    @Override
    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse findById(Long id) {
        return orderRepository.findById(id)
                .map(this::mapToResponse)
                .orElse(null);
    }

    @Override
    public OrderResponse save(OrderRequest request) {
        Order order = mapToEntity(request);
        order = orderRepository.save(order);
        return mapToResponse(order);
    }

    @Override
    public OrderResponse save(Long id, OrderRequest request) {
        return orderRepository.findById(id)
                .map(existingEntity -> partialUpdate(existingEntity, request))
                .map(orderRepository::save)
                .map(this::mapToResponse)
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void delete(List<Long> ids) {
        orderRepository.deleteAllById(ids);
    }

    private Order mapToEntity(OrderRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        OrderCancellationReason orderCancellationReason = orderCancellationReasonRepository.findById(request.getOrderCancellationReasonId())
                .orElse(null);

        Order order = new Order();
        order.setCode(RandomString.make(12).toUpperCase());
        order.setStatus(request.getStatus());
        order.setUser(user);
        order.setTotalAmount(request.getTotalAmount());
        order.setTax(request.getTax());
        order.setTotalPay(request.getTotalPay());
        order.setPaymentMethodType(request.getPaymentMethodType());
        order.setPaymentStatus(request.getPaymentStatus());

        order.setNote(request.getNote());
        order.setOrderCancellationReason(orderCancellationReason);

        Set<OrderVariant> orderVariants = request.getOrderVariants().stream()
                .map(orderVariantRequest -> {
                    OrderVariant orderVariant = new OrderVariant();
                    Variant variant = variantRepository.findById(orderVariantRequest.getVariantId())
                            .orElseThrow(() -> new RuntimeException("Variant not found with ID: " + orderVariantRequest.getVariantId()));
                    orderVariant.setVariant(variant);
                    orderVariant.setPrice(orderVariantRequest.getPrice());
                    orderVariant.setQuantity(orderVariantRequest.getQuantity());
                    orderVariant.setAmount(orderVariantRequest.getAmount());
                    return orderVariant;
                })
                .collect(Collectors.toSet());
        order.setOrderVariants(orderVariants);
        return order;
    }


    private Order partialUpdate(Order order, OrderRequest request) {
        order.setStatus(request.getStatus());
        order.setTotalAmount(request.getTotalAmount());
        order.setTax(request.getTax());
        order.setTotalPay(request.getTotalPay());
        order.setPaymentStatus(request.getPaymentStatus());
        order.setPaymentStatus(request.getPaymentStatus());

        return order;
    }

    private OrderResponse mapToResponse(Order order) {

        User user = order.getUser();

        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setCreatedAt(order.getCreatedAt());
        response.setUpdatedAt(order.getUpdatedAt());
        response.setCode(order.getCode());
        response.setStatus(order.getStatus());

        response.setTotalAmount(order.getTotalAmount());
        response.setTax(order.getTax());
        response.setTotalPay(order.getTotalPay());
        response.setPaymentMethodType(order.getPaymentMethodType());
        response.setPaymentStatus(order.getPaymentStatus());

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());
        userResponse.setUsername(user.getUsername());
        userResponse.setPassword(user.getPassword());
        userResponse.setGmail(user.getEmail());
        response.setUser(userResponse);

        return response;
    }
}
