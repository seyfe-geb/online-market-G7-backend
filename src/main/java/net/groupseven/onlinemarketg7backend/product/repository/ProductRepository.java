package net.groupseven.onlinemarketg7backend.product.repository;

import com.online.market.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByUserId(long userId);
}
