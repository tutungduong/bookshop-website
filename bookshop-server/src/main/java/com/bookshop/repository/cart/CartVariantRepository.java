package com.bookshop.repository.cart;

import com.bookshop.entity.cart.CartVariant;
import com.bookshop.entity.cart.CartVariantKey;
import com.bookshop.entity.product.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartVariantRepository extends JpaRepository<CartVariant, CartVariantKey>,
        JpaSpecificationExecutor<CartVariant> {}