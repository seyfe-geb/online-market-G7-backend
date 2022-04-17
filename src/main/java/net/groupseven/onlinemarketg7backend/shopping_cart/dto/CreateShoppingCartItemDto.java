package net.groupseven.onlinemarketg7backend.shopping_cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateShoppingCartItemDto {
    private long id;

    private int quantity;

    private long productId;
}
