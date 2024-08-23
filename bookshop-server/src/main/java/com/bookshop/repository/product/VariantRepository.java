package com.bookshop.repository.product;


import com.bookshop.entity.product.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long>, JpaSpecificationExecutor<Variant> {
}
