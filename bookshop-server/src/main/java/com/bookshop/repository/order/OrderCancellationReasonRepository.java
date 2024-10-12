package com.bookshop.repository.order;

import com.bookshop.entity.order.OrderCancellationReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCancellationReasonRepository extends JpaRepository<OrderCancellationReason, Long>,
        JpaSpecificationExecutor<OrderCancellationReason> {}