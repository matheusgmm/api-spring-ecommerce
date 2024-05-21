package com.muccio.repositories;

import com.muccio.models.entities.OrderItem;
import com.muccio.models.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
