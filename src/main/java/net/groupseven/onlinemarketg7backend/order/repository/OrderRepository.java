package net.groupseven.onlinemarketg7backend.order.repository;

import com.online.market.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(long userId);

    @Query(name = "findSellerOrders",
            value = "SELECT o.id "
                    + " FROM orders o INNER JOIN order_items i ON  i.order_id = o.id"
                    + " INNER JOIN products p ON i.product_id = p.id"
                    + " WHERE p.user_id = ?1",
            nativeQuery = true)
    List<Long> findSellerOrders(long userId);
}
