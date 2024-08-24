package com.bookshop.repository.cart;


import com.bookshop.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long>, JpaSpecificationExecutor<Cart> {
    @Query("SELECT c FROM Cart c JOIN c.user u WHERE u.username = :username AND c.status = 1")
    Optional<Cart> findByUsername(@Param("username") String username);
}
