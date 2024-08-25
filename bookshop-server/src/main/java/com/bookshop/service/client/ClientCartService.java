package com.bookshop.service.client;

import com.bookshop.dto.client.ClientCartRequest;
import com.bookshop.dto.client.ClientCartResponse;
import com.bookshop.dto.client.ClientCartVariantKeyRequest;
import com.bookshop.dto.client.UpdateQuantityType;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public interface ClientCartService {

     List<ClientCartResponse> get(Long userId);
     ClientCartResponse save (ClientCartRequest request);
     void delete(List<ClientCartVariantKeyRequest> ids);
}
