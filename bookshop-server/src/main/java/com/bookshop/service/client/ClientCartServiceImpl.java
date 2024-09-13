package com.bookshop.service.client;


import com.bookshop.dto.client.*;
import com.bookshop.entity.authentication.User;
import com.bookshop.entity.cart.Cart;
import com.bookshop.entity.cart.CartVariant;
import com.bookshop.entity.cart.CartVariantKey;
import com.bookshop.entity.product.Variant;
import com.bookshop.repository.authentication.UserRepository;
import com.bookshop.repository.cart.CartRepository;
import com.bookshop.repository.cart.CartVariantRepository;
import com.bookshop.repository.product.VariantRepository;
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
    private final VariantRepository variantRepository;
    private final UserRepository userRepository;


    @Override
    public List<ClientCartResponse> get(String username) {

        return cartRepository.findByUsername(username).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClientCartResponse save(ClientCartRequest request) {
        Cart cart;

        if (request.getCartId() != null) {
            cart = cartRepository.findById(request.getCartId())
                    .map(existingEntity -> partialUpdate(existingEntity, request))
                    .orElseGet(() -> mapToEntity(request));
        } else {
            cart = mapToEntity(request);
        }

        cart = cartRepository.save(cart);
        return mapToResponse(cart);
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

    if(request.getUserId() == null){
        throw new RuntimeException("User ID is required");
    }

    User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUserId()));

    cart.setUser(user);
    cart.setStatus(request.getStatus());

    Set<CartVariant> cartVariants = request.getCartItems().stream()
        .map(clientCartVariantRequest -> {
            CartVariant cartVariant = new CartVariant();
            cartVariant.setQuantity(clientCartVariantRequest.getQuantity());

            if(clientCartVariantRequest.getVariantId() == null){
                throw new RuntimeException("Variant ID is required");
            }
            Variant variant = variantRepository.findById(clientCartVariantRequest.getVariantId())
                .orElseThrow(() -> new RuntimeException("Variant not found with ID: "
                     + clientCartVariantRequest.getVariantId()));

            cartVariant.setVariant(variant);
            cartVariant.setCart(cart);
            return cartVariant;
        }).collect(Collectors.toSet());

    cart.setCartVariants(cartVariants);

    return cart;
}

//  private Cart partialUpdate(Cart cart, ClientCartRequest request) {
//
//    if(request.getUserId() == null){
//            throw new RuntimeException("User ID is required");
//    }
//
//    User user = userRepository.findById(request.getUserId())
//            .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUserId()));
//
//    cart.setUser(user);
//    cart.setStatus(request.getStatus());
//
//       if (request.getUpdateQuantityType() != null) {
//        Set<CartVariant> cartVariants = request.getCartItems().stream()
//            .map(clientCartVariantRequest -> {
//                if (clientCartVariantRequest.getVariantId() == null) {
//                    throw new RuntimeException("Variant ID is required");
//                }
//
//                Variant variant = variantRepository.findById(clientCartVariantRequest.getVariantId())
//                    .orElseThrow(() -> new RuntimeException("Variant not found with ID: "
//                        + clientCartVariantRequest.getVariantId()));
//
//                CartVariant cartVariant = new CartVariant();
//                cartVariant.setQuantity(clientCartVariantRequest.getQuantity());
//                cartVariant.setVariant(variant);
//                cartVariant.setCart(cart);
//
//                return cartVariant;
//            })
//            .collect(Collectors.toSet());
//
//        if (request.getUpdateQuantityType() == UpdateQuantityType.OVERRIDE) {
//            cart.getCartVariants().clear();
//            cart.setCartVariants(cartVariants);
//        } else if (request.getUpdateQuantityType() == UpdateQuantityType.INCREMENTAL) {
//            cart.setCartVariants(cartVariants);
//        }
//    } else {
//        throw new RuntimeException("Update Quantity Type is required");
//    }
//    return cart;
//}


      private Cart partialUpdate(Cart cart, ClientCartRequest request) {

    if (request.getUserId() != null) {
        cart.setUser(userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    if (request.getCartItems() != null && !request.getCartItems().isEmpty()) {
        Set<CartVariant> cartVariants = request.getCartItems().stream()
            .map(clientCartVariantRequest -> {
                CartVariant cartVariant = new CartVariant();
                cartVariant.setQuantity(clientCartVariantRequest.getQuantity());

                // Find the variant and set it to cartVariant
                Variant variant = variantRepository.findById(clientCartVariantRequest.getVariantId())
                    .orElseThrow(() -> new RuntimeException("Variant not found with ID: "
                         + clientCartVariantRequest.getVariantId()));
                cartVariant.setVariant(variant);

                // Set the cart to the cartVariant
                cartVariant.setCart(cart);

                return cartVariant;
            })
            .collect(Collectors.toSet());

        cart.setCartVariants(cartVariants);
    }
    cart.setStatus(request.getStatus());

    return cart;
}

    private ClientCartResponse mapToResponse(Cart cart) {
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
                    clientProductResponse.setAuthor(cartVariant.getVariant().getProduct().getAuthor());
                    clientVariantResponse.setVariantProduct(clientProductResponse);
                    clientCartVariantResponse.setCartItemVariant(clientVariantResponse);

                    return clientCartVariantResponse;
                })
                .collect(Collectors.toSet());

        response.setCartItems(cartVariantResponses);
        response.setStatus(cart.getStatus());
        return response;
    }
}