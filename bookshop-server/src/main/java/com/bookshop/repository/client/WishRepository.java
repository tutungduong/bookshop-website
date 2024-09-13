package com.bookshop.repository.client;

import com.bookshop.entity.cart.Cart;
import com.bookshop.entity.general.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish, Long>, JpaSpecificationExecutor<Wish> {

 Optional<Wish> findByUser_IdAndProduct_Id(Long userId, Long productId);


}
