package com.bookshop.repository.product;


import com.bookshop.entity.product.Category;
import com.bookshop.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> ,  JpaSpecificationExecutor<Category> {

}
