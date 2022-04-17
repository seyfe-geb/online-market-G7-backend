package net.groupseven.onlinemarketg7backend.shopping_cart.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import net.groupseven.onlinemarketg7backend.product.model.Product;

import javax.persistence.*;

@Entity
@Table(name = "shopping_cart_items")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_cart_id", nullable = false)
    @JsonBackReference
    private ShoppingCart cart;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
