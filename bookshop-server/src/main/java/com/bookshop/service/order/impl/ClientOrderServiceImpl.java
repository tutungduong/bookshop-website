package com.bookshop.service.order.impl;


import com.bookshop.config.payment.VNPAY.VNPAYHttpClient;
import com.bookshop.constant.AppConstants;
import com.bookshop.constant.FieldName;
import com.bookshop.constant.ResourceName;
import com.bookshop.dto.ListResponse;
import com.bookshop.dto.client.*;
import com.bookshop.dto.payment.OrderIntent;
import com.bookshop.dto.payment.VNPAY.VNPAYRequest;
import com.bookshop.dto.payment.VNPAY.VNPAYResponse;
import com.bookshop.entity.authentication.User;
import com.bookshop.entity.cart.Cart;
import com.bookshop.entity.cashbook.PaymentMethodType;
import com.bookshop.entity.order.Order;
import com.bookshop.entity.order.OrderVariant;
import com.bookshop.entity.promotion.Promotion;
import com.bookshop.exception.ResourceNotFoundException;
import com.bookshop.repository.authentication.UserRepository;
import com.bookshop.repository.cart.CartRepository;
import com.bookshop.repository.order.OrderRepository;
import com.bookshop.repository.promotion.PromotionRepository;
import com.bookshop.repository.review.ReviewRepository;
import com.bookshop.service.order.ClientOrderService;
import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientOrderServiceImpl implements ClientOrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final PromotionRepository promotionRepository;
    private final ReviewRepository reviewRepository;

    private final VNPAYHttpClient vnpayHttpClient;


    @Override
    public ListResponse<ClientSimpleOrderResponse> getAllOrders(String username, int page, int size, String sort, String filter) {
        Page<Order> orders = orderRepository.findAllByUsername(username, sort, filter, PageRequest.of(page - 1, size));
        List<ClientSimpleOrderResponse> entityResponse = orders.getContent().stream()
                .map(this::entityToOrderResponse)
                .collect(Collectors.toList());
        return new ListResponse<>(entityResponse, orders);
    }


    @Override
    public void cancelOrder(String code) {
        Order order = orderRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceName.ORDER, FieldName.ID,code));

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        Cart cart = cartRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceName.CART, FieldName.USERNAME, username));

        // (1) Tạo đơn hàng
        Order order = new Order();

        order.setCode(RandomString.make(12).toUpperCase());
        order.setStatus(1); // Status 1: Đơn hàng mới
        order.setToName(user.getFullname());
        order.setToPhone(user.getPhone());
        order.setToAddress(user.getAddress());
        order.setUser(user);


          order.setOrderVariants(cart.getCartVariants().stream()
                .map(cartVariant -> {
                    Promotion promotion = promotionRepository
                            .findActivePromotionByProductId(cartVariant.getVariant().getProduct().getId())
                            .stream()
                            .findFirst()
                            .orElse(null);

                    double currentPrice = calculateDiscountedPrice(cartVariant.getVariant().getPrice(),
                            promotion == null ? 0 : promotion.getPercent());

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

         BigDecimal tax = BigDecimal.valueOf(AppConstants.DEFAULT_TAX);

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
        // (3) Kiểm tra hình thức thanh toán
      if (request.getPaymentMethodType() == PaymentMethodType.CASH) {
             orderRepository.save(order);
      }
      else if (request.getPaymentMethodType() == PaymentMethodType.VNPAY) {

           try{
               // (3.2.1) Tính tổng tiền theo VND
               VNPAYRequest vnpayRequest = new VNPAYRequest();

               vnpayRequest.setIntent(OrderIntent.CAPTURE);

               vnpayRequest.setAmount(String.valueOf(order.getTotalPay().intValue()));

               vnpayRequest.setApplicationContext(new VNPAYRequest.VNPAYAppContext()
                            .setReturnUrl(AppConstants.BACKEND_HOST + "/client-api/orders/success")
                            .setCancelUrl(AppConstants.BACKEND_HOST + "/client-api/orders/cancel"));

               VNPAYResponse vnpayResponse = vnpayHttpClient.createPaypalTransaction(vnpayRequest);


                // (3.2.4) Trả về đường dẫn checkout cho user
                response.setOrderPaypalCheckoutLink(vnpayResponse.getPaymentUrl());

                orderRepository.save(order);
          }
          catch (Exception e) {
                throw new RuntimeException("Cannot create VNPAY transaction request!" + e);
            }
      }
      else if (request.getPaymentMethodType() == PaymentMethodType.PAYPAL) {
            // Will update Method Payment PayPal later
          try{
                orderRepository.save(order);
          }
          catch (Exception e) {
                throw new RuntimeException("Cannot create PayPal transaction request!" + e);
            }
      }
      else{
           throw new RuntimeException("Cannot identify payment method");
      }
       // (4) Vô hiệu cart
       cart.setStatus(2); // Status 2: Vô hiệu lực
       cartRepository.save(cart);

       return response;
    }

    @Override
    public ClientOrderDetailResponse getCode(String code) {

         Order order = orderRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceName.ORDER, FieldName.ID,code));

        return entityToOrderDetailResponse(order);

    }

    private ClientSimpleOrderResponse entityToOrderResponse(Order order) {
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
                    clientProductResponse.setProductSlug(orderVariant.getVariant().getProduct().getSlug());

//
//                    clientProductResponse.setProductThumbnail(orderVariant.getVariant().getProduct().getImages().stream()
//                        .filter(Image::getIsThumbnail)
//                        .findFirst()
//                        .map(Image::getPath)
//                        .orElseThrow(() -> new ResourceNotFoundException(ResourceName.IMAGE, FieldName.PRODUCT_ID, orderVariant.getVariant().getProduct().getId())));
//
//                    Review review = reviewRepository.findById(orderVariant.getVariant().getProduct().getId())
//                        .orElseThrow(() -> new ResourceNotFoundException(ResourceName.REVIEW, FieldName.PRODUCT_ID, orderVariant.getVariant().getProduct().getId()));
//
//                    clientProductResponse.setProductIsReviewed(review != null);

                    clientVariantResponse.setVariantProduct(clientProductResponse);
                    clientOrderVariantResponse.setOrderItemVariant(clientVariantResponse);

                    return clientOrderVariantResponse;
                })
                .collect(Collectors.toSet());
        response.setOrderItems(clientOrderVariantResponses);
        return response;
    }

    private ClientOrderDetailResponse entityToOrderDetailResponse(Order order) {
        ClientOrderDetailResponse response = new ClientOrderDetailResponse();

        response.setOrderId(order.getId());
        response.setOrderCreatedAt(order.getCreatedAt());
        response.setOrderCode(order.getCode());
        response.setOrderStatus(order.getStatus());
        response.setOrderToName(order.getToName());
        response.setOrderToPhone(order.getToPhone());
        response.setOrderToAddress(order.getToAddress());
        response.setOrderTotalAmount(order.getTotalAmount());
        response.setOrderTax(order.getTax());
        response.setOrderTotalPay(order.getTotalPay());
        response.setOrderPaymentMethodType(order.getPaymentMethodType());
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
                    clientProductResponse.setProductSlug(orderVariant.getVariant().getProduct().getSlug());


//                    clientProductResponse.setProductThumbnail(orderVariant.getVariant().getProduct().getImages().stream()
//                        .filter(Image::getIsThumbnail)
//                        .findFirst()
//                        .map(Image::getPath)
//                        .orElseThrow(() -> new ResourceNotFoundException(ResourceName.IMAGE, FieldName.PRODUCT_ID, orderVariant.getVariant().getProduct().getId())));
//
//                    Review review = reviewRepository.findById(orderVariant.getVariant().getProduct().getId())
//                        .orElseThrow(() -> new ResourceNotFoundException(ResourceName.REVIEW, FieldName.PRODUCT_ID, orderVariant.getVariant().getProduct().getId()));
//
//                    clientProductResponse.setProductIsReviewed(review != null);

                    clientVariantResponse.setVariantProduct(clientProductResponse);
                    clientOrderVariantResponse.setOrderItemVariant(clientVariantResponse);

                    return clientOrderVariantResponse;
                })
                .collect(Collectors.toSet());
        response.setOrderItems(clientOrderVariantResponses);

        return response;
    }

       private Double calculateDiscountedPrice(Double price, Integer discount) {
        return price * (100 - discount) / 100;
    }
}
