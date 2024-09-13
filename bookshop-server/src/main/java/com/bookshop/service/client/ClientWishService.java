package com.bookshop.service.client;

import com.bookshop.dto.client.ClientWishRequest;
import com.bookshop.dto.client.ClientWishResponse;

import java.util.List;


public interface ClientWishService {
    List<ClientWishResponse> get(String username);
    ClientWishResponse save(ClientWishRequest request);
    void delete(List<Long> ids);
}
