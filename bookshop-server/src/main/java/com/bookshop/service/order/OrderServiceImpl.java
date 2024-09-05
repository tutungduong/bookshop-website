package com.bookshop.service.order;


import com.bookshop.dto.client.*;
import com.bookshop.entity.authentication.User;
import com.bookshop.entity.cart.Cart;
import com.bookshop.entity.cashbook.PaymentMethodType;
import com.bookshop.entity.order.Order;
import com.bookshop.entity.order.OrderVariant;
import com.bookshop.repository.authentication.UserRepository;
import com.bookshop.repository.cart.CartRepository;
import com.bookshop.repository.order.OrderRepository;
import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;


    @Override
    public void cancelOrder(String code) {
        Order order = orderRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Hủy đơn hàng khi status = 1 hoặc 2
        if(order.getStatus() < 3){
            order.setStatus(5); // Status 5 là trạng thái huỷ háng

            orderRepository.save(order);
        }
         else {
            throw new RuntimeException(String
                    .format("Order with code %s is in delivery or has been cancelled. Please check again!", code));
        }
    }

    @Override
    public ClientConfirmedOrderResponse createClientOrder(ClientSimpleOrderRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String username = user.getUsername();

        Cart cart = cartRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // (1) Tạo đơn hàng

        Order order = new Order();
        order.setCode(RandomString.make(12).toUpperCase());
        order.setStatus(1);
        order.setUser(user);

         order.setOrderVariants(cart.getCartVariants().stream()
                .map(cartVariant -> {
                    double currentPrice = cartVariant.getVariant().getPrice();
//                    double currentPrice = calculateDiscountedPrice(cartVariant.getVariant().getPrice(),
//                            promotion == null ? 0 : promotion.getPercent());

                    return new OrderVariant()
                            .setOrder(order)
                            .setVariant(cartVariant.getVariant())
                            .setPrice(BigDecimal.valueOf(currentPrice))
                            .setQuantity(cartVariant.getQuantity())
                            .setAmount(BigDecimal.valueOf(currentPrice).multiply(BigDecimal.valueOf(cartVariant.getQuantity())));
                })
                .collect(Collectors.toSet()));

          // Calculate price values

        BigDecimal totalAmount = BigDecimal.valueOf(order.getOrderVariants().stream()
                .mapToDouble(orderVariant -> orderVariant.getAmount().doubleValue())
                .sum());

         BigDecimal tax = BigDecimal.valueOf(0.1);
         BigDecimal totalPay = totalAmount
                .add(totalAmount.multiply(tax).setScale(0, RoundingMode.HALF_UP));

         order.setTotalAmount(totalAmount);
         order.setTax(tax);
         order.setTotalPay(totalPay);
         order.setPaymentMethodType(request.getPaymentMethodType());
         order.setPaymentStatus(1); // Status 1: Chưa thanh toán

        // (2) Tạo response
        ClientConfirmedOrderResponse response = new ClientConfirmedOrderResponse();

        response.setOrderCode(order.getCode());
        response.setOrderPaymentMethodType(order.getPaymentMethodType());

      if (request.getPaymentMethodType() == PaymentMethodType.CASH) {
             orderRepository.save(order);
         }
         else{

             // will update Method Payment Paypal later
              throw new RuntimeException("Cannot identify payment method");
        }
       // (4) Vô hiệu cart
            cart.setStatus(2); // Status 2: Vô hiệu lực
            cartRepository.save(cart);

            return response;
    }

    @Override
    public List<ClientSimpleOrderResponse> get(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String username = user.getUsername();

        return orderRepository.findByUsername(username).stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }

    private ClientSimpleOrderResponse entityToResponse(Order order) {
        ClientSimpleOrderResponse response = new ClientSimpleOrderResponse();

        response.setOrderId(order.getId());
        response.setOrderCreatedAt(order.getCreatedAt());
        response.setOrderCode(order.getCode());
        response.setOrderStatus(order.getStatus());
        response.setOrderTotalPay(order.getTotalPay());
        response.setOrderPaymentStatus(order.getPaymentStatus());

        Set<ClientOrderVariantResponse> clientOrderVariantResponses = order.getOrderVariants().stream()
                .map(orderVariant -> {
                    ClientOrderVariantResponse clientOrderVariantResponse = new ClientOrderVariantResponse();

                    clientOrderVariantResponse.setOrderItemPrice(orderVariant.getPrice());
                    clientOrderVariantResponse.setOrderItemQuantity(orderVariant.getQuantity());
                    clientOrderVariantResponse.setOrderItemAmount(orderVariant.getAmount());

                    ClientOrderVariantResponse.ClientVariantResponse clientVariantResponse = new ClientOrderVariantResponse.ClientVariantResponse();
                    clientVariantResponse.setVariantId(orderVariant.getVariant().getId());

                    ClientOrderVariantResponse.ClientVariantResponse.ClientProductResponse clientProductResponse = new ClientOrderVariantResponse.ClientVariantResponse.ClientProductResponse();
                    clientProductResponse.setProductId(orderVariant.getVariant().getProduct().getId());
                    clientProductResponse.setProductName(orderVariant.getVariant().getProduct().getName());

                    clientVariantResponse.setVariantProduct(clientProductResponse);
                    clientOrderVariantResponse.setOrderItemVariant(clientVariantResponse);

                    return clientOrderVariantResponse;
                })
                .collect(Collectors.toSet());
        response.setOrderItems(clientOrderVariantResponses);
        return response;
    }
}
