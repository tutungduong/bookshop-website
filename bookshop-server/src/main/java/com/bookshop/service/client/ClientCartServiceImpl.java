package com.bookshop.service.client;


import com.bookshop.dto.client.ClientCartRequest;
import com.bookshop.dto.client.ClientCartResponse;
import com.bookshop.dto.client.ClientCartVariantKeyRequest;
import com.bookshop.dto.client.ClientCartVariantResponse;
import com.bookshop.entity.authentication.User;
import com.bookshop.entity.cart.Cart;
import com.bookshop.entity.cart.CartVariant;
import com.bookshop.entity.cart.CartVariantKey;
import com.bookshop.entity.product.Variant;
import com.bookshop.repository.authentication.UserRepository;
import com.bookshop.repository.cart.CartRepository;
import com.bookshop.repository.cart.CartVariantRepository;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientCartServiceImpl implements ClientCartService {
    private final CartRepository cartRepository;
    private final CartVariantRepository cartVariantRepository;
    private final UserRepository userRepository;
    @Override
    public List<ClientCartResponse> get(String username) {
        return cartRepository.findByUsername(username).stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClientCartResponse save(ClientCartRequest request) {
        final Cart cartBeforeSave;

        if(request.getCartId() != null){
            cartBeforeSave = responseToEntity(request, request.getUserId());
        }
        else{
            cartBeforeSave = cartRepository.findById(request.getCartId())
                    .map(existingEntity -> partialUpdate(existingEntity, request))
                    .orElse(responseToEntity(request, request.getUserId()));
        }
        Cart cart = cartRepository.save(cartBeforeSave);
        return entityToResponse(cart);
    }

    @Override
    public void delete(List<ClientCartVariantKeyRequest> ids) {
         List<CartVariantKey> id = ids.stream()
                .map(idRequest -> new CartVariantKey(idRequest.getCartId(), idRequest.getVariantId()))
                .collect(Collectors.toList());
        cartVariantRepository.deleteAllById(id);
    }


    private Cart responseToEntity(ClientCartRequest request, Long userId){
        Cart cart = new Cart();
        cart.setUser(userRepository.findById(userId).orElseThrow());
        cart.setCartVariants(request.getCartItems().stream()
                .map(clientCartVariantRequest -> {
                    CartVariant cartVariant = new CartVariant();
                    cartVariant.setQuantity(clientCartVariantRequest.getQuantity());
                    cartVariant.setVariant(cartVariantRepository.findById(clientCartVariantRequest.getVariantId()).orElseThrow());
                    return cartVariant;
                })
                .collect(Collectors.toSet()));
        cart.setStatus(request.getStatus());
        return cart;
    }

    private Cart partialUpdate(Cart cart, ClientCartRequest request){
        cart.setCartVariants(request.getCartItems().stream()
                .map(clientCartVariantRequest -> {
                    CartVariant cartVariant = new CartVariant();
                    cartVariant.setQuantity(clientCartVariantRequest.getQuantity());
                    cartVariant.setVariant(cartVariantRepository.findById(clientCartVariantRequest.getVariantId()).orElseThrow());
                    return cartVariant;
                })
                .collect(Collectors.toSet()));
        cart.setStatus(request.getStatus());
        return cart;
    }

    private ClientCartResponse entityToResponse(Cart cart) {
        ClientCartResponse response = new ClientCartResponse();
        response.setCartId(cart.getId());

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

                    clientVariantResponse.setVariantProduct(clientProductResponse);
                    clientCartVariantResponse.setCartItemVariant(clientVariantResponse);

                    return clientCartVariantResponse;
                })
                .collect(Collectors.toSet());

        response.setCartItems(cartVariantResponses);
        return response;
    }
}
