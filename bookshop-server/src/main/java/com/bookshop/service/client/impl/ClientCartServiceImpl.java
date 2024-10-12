package com.bookshop.service.client.impl;


import com.bookshop.constant.FieldName;
import com.bookshop.constant.ResourceName;
import com.bookshop.dto.client.ClientCartRequest;
import com.bookshop.dto.client.ClientCartResponse;
import com.bookshop.dto.client.ClientCartVariantKeyRequest;
import com.bookshop.dto.client.ClientCartVariantResponse;
import com.bookshop.entity.authentication.User;
import com.bookshop.entity.cart.Cart;
import com.bookshop.entity.cart.CartVariant;
import com.bookshop.entity.cart.CartVariantKey;
import com.bookshop.entity.product.Variant;
import com.bookshop.exception.ResourceNotFoundException;
import com.bookshop.repository.authentication.UserRepository;
import com.bookshop.repository.cart.CartRepository;
import com.bookshop.repository.cart.CartVariantRepository;
import com.bookshop.repository.product.VariantRepository;
import com.bookshop.repository.promotion.PromotionRepository;
import com.bookshop.service.client.ClientCartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientCartServiceImpl implements ClientCartService {
    private final CartRepository cartRepository;
    private final CartVariantRepository cartVariantRepository;
    private final VariantRepository variantRepository;
    private final UserRepository userRepository;
    private final PromotionRepository promotionRepository;


    @Override
    public List<ClientCartResponse> get(String username) {

        return cartRepository.findByUsername(username).stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClientCartResponse save(ClientCartRequest request) {
        final Cart cartBeforeSave;

        if (request.getCartId() != null) {
            cartBeforeSave = cartRepository.findById(request.getCartId())
                    .map(existingEntity -> partialUpdate(existingEntity, request))
                    .orElseThrow(() -> new ResourceNotFoundException(ResourceName.CART, FieldName.ID, request.getCartId()));
        } else {
            cartBeforeSave = mapToEntity(request);
        }

        Cart  cart = cartRepository.save(cartBeforeSave);
        return entityToResponse(cart);
    }

    @Override
    public void delete(List<ClientCartVariantKeyRequest> ids) {
         List<CartVariantKey> id = ids.stream()
                .map(idRequest -> new CartVariantKey(idRequest.getCartId(), idRequest.getVariantId()))
                .collect(Collectors.toList());
        cartVariantRepository.deleteAllById(id);
    }

    private Cart mapToEntity(ClientCartRequest request) {

    Cart cart = new Cart();

    User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.USER, FieldName.ID, request.getCartId()));

    cart.setUser(user);
    cart.setStatus(request.getStatus());

    Set<CartVariant> cartVariants = request.getCartItems().stream()
            .map(clientCartVariantRequest -> {
                CartVariant cartVariant = new CartVariant();
                cartVariant.setQuantity(clientCartVariantRequest.getQuantity());

                Variant variant = variantRepository.findById(clientCartVariantRequest.getVariantId())
                        .orElseThrow(() -> new ResourceNotFoundException(ResourceName.VARIANT, FieldName.ID,
                                clientCartVariantRequest.getVariantId()));

                cartVariant.setVariant(variant);
                cartVariant.setCart(cart);
                return cartVariant;
            }).collect(Collectors.toSet());

    cart.setCartVariants(cartVariants);
    return cart;
}


    private Cart partialUpdate(Cart cart, ClientCartRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(ResourceName.USER, FieldName.ID, request.getCartId()));

        cart.setUser(user);
        cart.setStatus(request.getStatus());

        cart.setCartVariants(request.getCartItems().stream()
                .map(clientCartVariantRequest -> {
                    CartVariant cartVariant = new CartVariant();
                    cartVariant.setQuantity(clientCartVariantRequest.getQuantity());

                    Variant variant = variantRepository.findById(clientCartVariantRequest.getVariantId())
                            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.VARIANT, FieldName.ID,
                                    clientCartVariantRequest.getVariantId()));

                    cartVariant.setVariant(variant);
                    cartVariant.setCart(cart);
                    return cartVariant;
                }).collect(Collectors.toSet()));

        cart.setUpdatedAt(Instant.now());

        return cart;
}

    private ClientCartResponse entityToResponse(Cart cart) {
        ClientCartResponse response = new ClientCartResponse();

        response.setCartId(cart.getId());
        response.setStatus(cart.getStatus());

        Set<ClientCartVariantResponse> cartVariantResponses = cart.getCartVariants().stream()
                .map(cartVariant -> {
                    ClientCartVariantResponse clientCartVariantResponse = new ClientCartVariantResponse();
                    clientCartVariantResponse.setCartItemQuantity(cartVariant.getQuantity());

                    ClientCartVariantResponse.ClientVariantResponse clientVariantResponse =
                            new ClientCartVariantResponse.ClientVariantResponse();
                    clientVariantResponse.setVariantId(cartVariant.getVariant().getId());
                    clientVariantResponse.setVariantPrice(cartVariant.getVariant().getPrice());
                    clientVariantResponse.setVariantDiscount(cartVariant.getVariant().getDiscount());

                    ClientCartVariantResponse.ClientVariantResponse.ClientProductResponse clientProductResponse =
                            new ClientCartVariantResponse.ClientVariantResponse.ClientProductResponse();
                    clientProductResponse.setProductId(cartVariant.getVariant().getProduct().getId());
                    clientProductResponse.setProductName(cartVariant.getVariant().getProduct().getName());
                    clientProductResponse.setProductSlug(cartVariant.getVariant().getProduct().getSlug());
                    clientProductResponse.setProductAuthor(cartVariant.getVariant().getProduct().getAuthor());

                    clientVariantResponse.setVariantProduct(clientProductResponse);
                    clientCartVariantResponse.setCartItemVariant(clientVariantResponse);


                    return clientCartVariantResponse;
                })
                .collect(Collectors.toSet());

        response.setCartItems(cartVariantResponses);

        return response;
    }
}