package com.bookshop.service.order;

import com.bookshop.dto.order.OrderRequest;
import com.bookshop.dto.order.OrderResponse;
import com.bookshop.dto.order.OrderVariantRequest;
import com.bookshop.dto.order.OrderVariantResponse;
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
        order.setOrderCancellationReason(orderCancellationReason);
        order.setNote(request.getNote());
        order.setUser(user);

        order.setTotalAmount(request.getTotalAmount());
        order.setTax(request.getTax());
        order.setTotalPay(request.getTotalPay());
        order.setPaymentMethodType(request.getPaymentMethodType());
        order.setPaymentStatus(request.getPaymentStatus());

        Set<OrderVariant> orderVariants = request.getOrderVariants().stream()
        .map(orderVariantRequest -> {

            Variant variant = variantRepository.findById(orderVariantRequest.getVariantId())
                    .orElseThrow(() -> new RuntimeException("Variant not found"));

            OrderVariant orderVariant = new OrderVariant();
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
        order.setPaymentMethodType(request.getPaymentMethodType());

        return order;
    }

    private OrderResponse mapToResponse(Order order) {

        OrderResponse response = new OrderResponse();

        response.setId(order.getId());
        response.setCreatedAt(order.getCreatedAt());
        response.setUpdatedAt(order.getUpdatedAt());
        response.setCode(order.getCode());
        response.setStatus(order.getStatus());
        response.setNote(order.getNote());

        OrderResponse.UserResponse userResponse = new OrderResponse.UserResponse();
        userResponse.setId(order.getUser().getId());
        userResponse.setCreatedAt(order.getUser().getCreatedAt());
        userResponse.setUpdatedAt(order.getUser().getUpdatedAt());
        userResponse.setUsername(order.getUser().getUsername());
        userResponse.setGmail(order.getUser().getEmail());

        response.setUser(userResponse);

        response.setTotalAmount(order.getTotalAmount());
        response.setTax(order.getTax());
        response.setTotalPay(order.getTotalPay());
        response.setPaymentMethodType(order.getPaymentMethodType());
        response.setPaymentStatus(order.getPaymentStatus());

        Set<OrderVariantResponse> orderVariantRequests = order.getOrderVariants().stream()
            .map(orderVariant -> {
                OrderVariantResponse orderVariantResponse = new OrderVariantResponse();
                orderVariantResponse.setPrice(orderVariant.getPrice());
                orderVariantResponse.setQuantity(orderVariant.getQuantity());
                orderVariantResponse.setAmount(orderVariant.getAmount());

                OrderVariantResponse.VariantResponse variantResponse = new OrderVariantResponse.VariantResponse();
                variantResponse.setId(orderVariant.getVariant().getId());
                variantResponse.setCreatedAt(orderVariant.getVariant().getCreatedAt());
                variantResponse.setUpdatedAt(orderVariant.getVariant().getUpdatedAt());
                variantResponse.setDiscount(orderVariant.getVariant().getDiscount());
                variantResponse.setPrice(orderVariant.getVariant().getPrice());
                variantResponse.setStatus(orderVariant.getVariant().getStatus());

                OrderVariantResponse.VariantResponse.ProductResponse productResponse = new OrderVariantResponse.VariantResponse.ProductResponse();
                productResponse.setId(orderVariant.getVariant().getProduct().getId());
                productResponse.setName(orderVariant.getVariant().getProduct().getName());
                productResponse.setAuthor(orderVariant.getVariant().getProduct().getAuthor());
                productResponse.setCreatedAt(orderVariant.getVariant().getProduct().getCreatedAt());
                productResponse.setUpdatedAt(orderVariant.getVariant().getProduct().getUpdatedAt());

                variantResponse.setProduct(productResponse);
                orderVariantResponse.setVariant(variantResponse);

                return orderVariantResponse;
            })
            .collect(Collectors.toSet());

        response.setOrderVariants(orderVariantRequests);
        return response;
    }
}
