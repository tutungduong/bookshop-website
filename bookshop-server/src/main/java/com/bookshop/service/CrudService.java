package com.bookshop.service;

import java.util.List;

public interface CrudService<ID,I,O> {
//    ListResponse<O> findAll(int page, int size, String sort, String filter, String search, boolean all);
    List<O> findAll();

    O findById(ID id);

    O save(I request);

    O save(ID id, I request);

    void delete(ID id);

    void delete(List<ID> ids);

}