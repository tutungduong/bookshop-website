package com.bookshop.service.order;

import com.bookshop.constant.AppConstants;
import com.bookshop.constant.FieldName;
import com.bookshop.constant.ResourceName;
import com.bookshop.constant.SearchFields;
import com.bookshop.dto.ListResponse;
import com.bookshop.dto.order.OrderCancellationReasonResponse;
import com.bookshop.dto.order.OrderRequest;
import com.bookshop.dto.order.OrderResponse;
import com.bookshop.dto.order.OrderVariantResponse;
import com.bookshop.entity.authentication.User;
import com.bookshop.entity.order.Order;
import com.bookshop.entity.order.OrderCancellationReason;
import com.bookshop.entity.order.OrderVariant;
import com.bookshop.entity.product.Variant;
import com.bookshop.exception.ResourceNotFoundException;
import com.bookshop.repository.authentication.UserRepository;
import com.bookshop.repository.order.OrderCancellationReasonRepository;
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
import java.time.Instant;
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
     public ListResponse<OrderResponse> findAll(int page, int size, String sort, String filter, String search, boolean all) {
        Specification<Order> sortable = RSQLJPASupport.toSort(sort);
        Specification<Order> filterable = RSQLJPASupport.toSpecification(filter);
        Specification<Order> searchable = SearchUtils.parse(search, SearchFields.ORDER);
        Pageable pageable = all ? Pageable.unpaged() : PageRequest.of(page - 1, size);
        Page<Order> entities = orderRepository.findAll(sortable.and(filterable).and(searchable), pageable);
        List<OrderResponse> entityResponse = entities.getContent().stream()
            .map(this::entityToResponse)
            .collect(Collectors.toList());
     return new ListResponse<>(entityResponse, entities);
    }

    @Override
    public OrderResponse findById(Long id) {
        return orderRepository.findById(id)
                .map(this::entityToResponse)
                .orElse(null);
    }

    @Override
    public OrderResponse save(OrderRequest request) {
        Order order = requestToEntity(request);
        order = orderRepository.save(order);
        return entityToResponse(order);
    }

    @Override
    public OrderResponse save(Long id, OrderRequest request) {
        return orderRepository.findById(id)
                .map(existingEntity -> partialUpdate(existingEntity, request))
                .map(orderRepository::save)
                .map(this::entityToResponse)
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

    private Order requestToEntity(OrderRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(ResourceName.USER, FieldName.ID,request.getUserId()));

        Variant variant = variantRepository.findById(request.getVariantId())
                .orElseThrow(() -> new ResourceNotFoundException(ResourceName.VARIANT, FieldName.ID,request.getVariantId()));

        OrderCancellationReason orderCancellationReason = orderCancellationReasonRepository.findById(request.getOrderCancellationReasonId())
                .orElseThrow(() -> new ResourceNotFoundException(ResourceName.ORDER_CANCELLATION_REASON, FieldName.ID,request.getOrderCancellationReasonId()));

        Order order = new Order();

        order.setCode(RandomString.make(12).toUpperCase());
        order.setStatus(request.getStatus());
        order.setToName(request.getToName());
        order.setToPhone(request.getToPhone());
        order.setToAddress(request.getToAddress());

        if(request.getOrderCancellationReasonId() != null){
            order.setOrderCancellationReason(orderCancellationReason);
        }
        order.setNote(request.getNote());
        order.setUser(user);

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

         BigDecimal tax = BigDecimal.valueOf(AppConstants.DEFAULT_TAX);
         BigDecimal totalPay = totalAmount
                .add(totalAmount.multiply(tax).setScale(0, RoundingMode.HALF_UP));

         order.setTotalAmount(totalAmount);
         order.setTax(tax);
         order.setTotalPay(totalPay);

        return order;
    }


    private Order partialUpdate(Order order, OrderRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(ResourceName.USER, FieldName.ID,request.getUserId()));

        Variant variant = variantRepository.findById(request.getVariantId())
                .orElseThrow(() -> new ResourceNotFoundException(ResourceName.VARIANT, FieldName.ID,request.getVariantId()));

        OrderCancellationReason orderCancellationReason = orderCancellationReasonRepository.findById(request.getOrderCancellationReasonId())
                .orElseThrow(() -> new ResourceNotFoundException(ResourceName.ORDER_CANCELLATION_REASON, FieldName.ID,request.getOrderCancellationReasonId()));

        order.setCode(RandomString.make(12).toUpperCase());
        order.setStatus(request.getStatus());
        order.setToName(request.getToName());
        order.setToPhone(request.getToPhone());
        order.setToAddress(request.getToAddress());

        if(request.getOrderCancellationReasonId() != null){
            order.setOrderCancellationReason(orderCancellationReason);
            order.setNote(request.getNote());
        }
        order.setUser(user);

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

         BigDecimal tax = BigDecimal.valueOf(AppConstants.DEFAULT_TAX);
         BigDecimal totalPay = totalAmount
                .add(totalAmount.multiply(tax).setScale(0, RoundingMode.HALF_UP));

         order.setTotalAmount(totalAmount);
         order.setTax(tax);
         order.setTotalPay(totalPay);
         order.setUpdatedAt(Instant.now());

    return order;
    }

    private OrderResponse entityToResponse(Order order) {

        OrderResponse response = new OrderResponse();

        response.setId(order.getId());
        response.setCreatedAt(order.getCreatedAt());
        response.setUpdatedAt(order.getUpdatedAt());
        response.setCode(order.getCode());

        response.setStatus(order.getStatus());

        OrderCancellationReasonResponse orderCancellationReasonResponse = new OrderCancellationReasonResponse();
        orderCancellationReasonResponse.setId(order.getOrderCancellationReason().getId());
        orderCancellationReasonResponse.setCreatedAt(order.getOrderCancellationReason().getCreatedAt());
        orderCancellationReasonResponse.setUpdatedAt(order.getOrderCancellationReason().getUpdatedAt());
        orderCancellationReasonResponse.setName(order.getOrderCancellationReason().getName());
        orderCancellationReasonResponse.setNote(order.getOrderCancellationReason().getNote());
        orderCancellationReasonResponse.setStatus(order.getOrderCancellationReason().getStatus());

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
                productResponse.setSlug(orderVariant.getVariant().getProduct().getSlug());

                variantResponse.setProduct(productResponse);
                orderVariantResponse.setVariant(variantResponse);

                return orderVariantResponse;
            })
            .collect(Collectors.toSet());

        response.setOrderVariants(orderVariantRequests);
        return response;
    }
}
