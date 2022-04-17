package net.groupseven.onlinemarketg7backend.shopping_cart.service;

import com.online.market.shopping_cart.dto.*;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCartDto findCart();

    ShoppingCartDto updateCart(SaveShoppingCartDto dto);

    List<ShoppingCartItemDto> findAllItems();
    ShoppingCartItemDto findItemById(long id);

    ShoppingCartItemDto addItem(CreateShoppingCartItemDto dto);
    ShoppingCartItemDto updateItemById(long id, UpdateShoppingCartItemDto dto);

    void deleteItemById(long id);
}
