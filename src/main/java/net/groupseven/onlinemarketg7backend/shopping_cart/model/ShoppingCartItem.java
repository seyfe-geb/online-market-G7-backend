package net.groupseven.onlinemarketg7backend.shopping_cart.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.online.market.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "shopping_cart_items")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_cart_id", nullable = false)
    @JsonBackReference
    private ShoppingCart cart;

    private int quantity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
