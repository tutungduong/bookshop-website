package com.bookshop.service.client;

import com.bookshop.dto.ListResponse;
import com.bookshop.dto.client.ClientWishRequest;
import com.bookshop.dto.client.ClientWishResponse;

import java.util.List;


public interface ClientWishService {
    ListResponse<ClientWishResponse> getAllWishes(String username, int page, int size, String sort, String filter);
    ClientWishResponse save(ClientWishRequest request);
    void delete(List<Long> ids);
}
