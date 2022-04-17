package net.groupseven.onlinemarketg7backend.shopping_cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaveShoppingCartDto {
    private Set<CreateShoppingCartItemDto> items = new HashSet<>();
}
