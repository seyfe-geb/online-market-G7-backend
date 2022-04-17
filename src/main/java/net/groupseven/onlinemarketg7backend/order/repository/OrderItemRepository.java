package net.groupseven.onlinemarketg7backend.order.repository;


import net.groupseven.onlinemarketg7backend.order.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByProductId(long prductId);
}
