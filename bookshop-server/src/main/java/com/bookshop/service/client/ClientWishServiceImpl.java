package com.bookshop.service.client;



import com.bookshop.dto.client.ClientWishRequest;
import com.bookshop.dto.client.ClientWishResponse;
import com.bookshop.entity.authentication.User;
import com.bookshop.entity.general.Wish;
import com.bookshop.entity.product.Product;
import com.bookshop.repository.authentication.UserRepository;
import com.bookshop.repository.client.WishRepository;
import com.bookshop.repository.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ClientWishServiceImpl implements ClientWishService {

    private final WishRepository wishRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public List<ClientWishResponse> get(Long userId) {

        return wishRepository.findById(userId)
                .stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClientWishResponse save(ClientWishRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));


        Wish wish = mapToEntity(request);
        wish = wishRepository.save(wish);
        return entityToResponse(wish);

    }

    @Override
    public void delete(List<Long> ids) {
        wishRepository.deleteAllById(ids);
    }


    private ClientWishResponse entityToResponse(Wish wish){
        ClientWishResponse response = new ClientWishResponse();
        response.setWishId(wish.getId());
        response.setWishCreatedAt(wish.getCreatedAt());

        ClientWishResponse.ClientListedProductResponse wishProduct = new ClientWishResponse.ClientListedProductResponse();
        wishProduct.setProductId(wish.getProduct().getId());
        wishProduct.setProductName(wish.getProduct().getName());

        List<ClientWishResponse.ClientListedProductResponse.ClientListedVariantResponse> productVariants = wish.getProduct().getVariants()
                .stream()
                .map(variant -> {
                    ClientWishResponse.ClientListedProductResponse.ClientListedVariantResponse responseVariant = new ClientWishResponse.ClientListedProductResponse.ClientListedVariantResponse();
                    responseVariant.setVariantId(variant.getId());
                    responseVariant.setVariantPrice(variant.getPrice());
                    return responseVariant;
                })
                .collect(Collectors.toList());

        wishProduct.setProductVariants(productVariants);
        response.setWishProduct(wishProduct);
        return response;
    }

    private Wish mapToEntity(ClientWishRequest request){
        Wish wish = new Wish();

        User user = userRepository.findById(request.getUserId()).orElseThrow();
        Product product = productRepository.findById(request.getProductId()).orElseThrow();

        wish.setUser(user);
        wish.setProduct(product);

        return wish;
    }
}
