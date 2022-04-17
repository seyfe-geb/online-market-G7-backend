package net.groupseven.onlinemarketg7backend.shopping_cart.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.online.market.product.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShoppingCartItemDto {
    private long id;

    @JsonBackReference
    private ShoppingCartDto cart;

    private int quantity;

    private ProductDto product;
}
