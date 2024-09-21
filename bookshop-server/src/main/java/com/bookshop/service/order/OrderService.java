package com.bookshop.service.order;

import com.bookshop.constant.SearchFields;
import com.bookshop.dto.ListResponse;
import com.bookshop.dto.order.OrderRequest;
import com.bookshop.dto.order.OrderResponse;
import com.bookshop.dto.order.OrderVariantResponse;
import com.bookshop.entity.authentication.User;
import com.bookshop.entity.order.Order;
import com.bookshop.entity.order.OrderVariant;
import com.bookshop.entity.product.Variant;
import com.bookshop.repository.authentication.UserRepository;
import com.bookshop.repository.order.OrderRepository;
import com.bookshop.repository.product.VariantRepository;
import com.bookshop.service.CrudService;
import com.bookshop.utils.SearchUtils;
import io.github.perplexhub.rsql.RSQLJPASupport;
import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderService implements CrudService<Long, OrderRequest, OrderResponse> {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
//    private final OrderCancellationReasonRepository orderCancellationReasonRepository;
    private final VariantRepository variantRepository;

    @Override
     public ListResponse<OrderResponse> findAll(int page, int size, String sort, String filter, String search, boolean all) {
        Specification<Order> sortable = RSQLJPASupport.toSort(sort);
        Specification<Order> filterable = RSQLJPASupport.toSpecification(filter);
        Specification<Order> searchable = SearchUtils.parse(search, SearchFields.ORDER);
        Pageable pageable = all ? Pageable.unpaged() : PageRequest.of(page - 1, size);
        Page<Order> entities = orderRepository.findAll(sortable.and(filterable).and(searchable), pageable);
        List<OrderResponse> entityResponse = entities.getContent().stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
     return new ListResponse<>(entityResponse, entities);
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

        Variant variant = variantRepository.findById(request.getVariantId()).orElseThrow(null);

//        OrderCancellationReason orderCancellationReason = orderCancellationReasonRepository.findById(request.getOrderCancellationReasonId())
//                .orElse(null);


        Order order = new Order();
        order.setCode(RandomString.make(12).toUpperCase());
        order.setUser(user);
        order.setStatus(request.getStatus());
//        order.setOrderCancellationReason(orderCancellationReason);
//        order.setNote(request.getNote());

        order.setPaymentMethodType(request.getPaymentMethodType());
        order.setPaymentStatus(request.getPaymentStatus());

        order.setOrderVariants(Set.of(new OrderVariant()
                .setOrder(order)
                .setVariant(variant)
                .setPrice(BigDecimal.valueOf(variant.getPrice()))
                .setQuantity(request.getQuantity())
                .setAmount(BigDecimal.valueOf(variant.getPrice()).multiply(BigDecimal.valueOf(request.getQuantity())))
        ));

        BigDecimal totalAmount = BigDecimal.valueOf(order.getOrderVariants().stream()
                .mapToDouble(orderVariant -> orderVariant.getAmount().doubleValue())
                .sum());

         BigDecimal tax = BigDecimal.valueOf(0.1);
         BigDecimal totalPay = totalAmount
                .add(totalAmount.multiply(tax).setScale(0, RoundingMode.HALF_UP));

         order.setTotalAmount(totalAmount);
         order.setTax(tax);
         order.setTotalPay(totalPay);

        return order;
    }


    private Order partialUpdate(Order order, OrderRequest request) {
         User user = userRepository.findById(request.getUserId()).orElse(null);
    Variant variant = variantRepository.findById(request.getVariantId()).orElse(null);

    order.setStatus(request.getStatus());
    order.setUser(user);

    order.setOrderVariants(Set.of(new OrderVariant()
                .setOrder(order)
                .setVariant(variant)
                .setPrice(BigDecimal.valueOf(variant.getPrice()))
                .setQuantity(request.getQuantity())
                .setAmount(BigDecimal.valueOf(variant.getPrice()).multiply(BigDecimal.valueOf(request.getQuantity())))
    ));

    order.setPaymentMethodType(request.getPaymentMethodType());
    order.setPaymentStatus(request.getPaymentStatus());

    BigDecimal totalAmount = BigDecimal.valueOf(order.getOrderVariants().stream()
            .mapToDouble(orderVariant -> orderVariant.getAmount().doubleValue())
            .sum());

    BigDecimal tax = BigDecimal.valueOf(0.1);
    BigDecimal totalPay = totalAmount
            .add(totalAmount.multiply(tax).setScale(0, RoundingMode.HALF_UP));

    order.setTotalAmount(totalAmount);
    order.setTax(tax);
    order.setTotalPay(totalPay);

    return order;
    }

    private OrderResponse mapToResponse(Order order) {

        OrderResponse response = new OrderResponse();

        response.setId(order.getId());
        response.setCreatedAt(order.getCreatedAt());
        response.setUpdatedAt(order.getUpdatedAt());
        response.setCode(order.getCode());
        response.setStatus(order.getStatus());
//        response.setNote(order.getNote());

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
