package net.groupseven.onlinemarketg7backend.shopping_cart.service;

import com.online.market.shopping_cart.dto.*;
import com.online.market.shopping_cart.model.ShoppingCart;
import com.online.market.shopping_cart.model.ShoppingCartItem;
import com.online.market.shopping_cart.repository.ShoppingCartRepository;
import com.online.market.util.service.LoggedInUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository repository;
    private final ModelMapper modelMapper;
    private final LoggedInUserService loggedInUserService;

    @Override
    public ShoppingCartDto findCart() {
        ShoppingCart cart = repository.findByUserId(loggedInUserService.getUserId())
                .orElse(null);

        return modelMapper.map(cart, ShoppingCartDto.class);
    }

    @Override
    public ShoppingCartDto updateCart(SaveShoppingCartDto dto) {
        Set<ShoppingCartItem> cartItems = dto.getItems().stream()
                .map(i -> modelMapper.map(i, ShoppingCartItem.class))
                .collect(Collectors.toSet());

        ShoppingCart cart = repository.findByUserId(loggedInUserService.getUserId())
                .orElse(null);

        for (ShoppingCartItem cartItem : cartItems)
            cartItem.setCart(cart);

        cart.setItems(cartItems);

        return modelMapper.map(repository.save(cart), ShoppingCartDto.class);
    }

    @Override
    public List<ShoppingCartItemDto> findAllItems() {
        ShoppingCart cart = repository.findByUserId(loggedInUserService.getUserId())
                .orElse(null);

        return cart.getItems().stream()
                .map(s -> modelMapper.map(s, ShoppingCartItemDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ShoppingCartItemDto findItemById(long id) {
        ShoppingCart cart = repository.findByUserId(loggedInUserService.getUserId())
                .orElse(null);

        ShoppingCartItem cartItem = cart.getItems().stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);

        return modelMapper.map(cartItem, ShoppingCartItemDto.class);
    }

    @Override
    public ShoppingCartItemDto addItem(CreateShoppingCartItemDto dto) {
        ShoppingCart cart = repository.findByUserId(loggedInUserService.getUserId())
                .orElse(null);

        cart.getItems().add(modelMapper.map(dto, ShoppingCartItem.class));
        repository.save(cart);

        return modelMapper.map(cart.getItems().toArray()[cart.getItems().size() - 1], ShoppingCartItemDto.class);
    }

    @Override
    public ShoppingCartItemDto updateItemById(long id, UpdateShoppingCartItemDto dto) {
        ShoppingCart cart = repository.findByUserId(loggedInUserService.getUserId())
                .orElse(null);

        ShoppingCartItem cartItem = cart.getItems().stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);

        cartItem.setQuantity(dto.getQuantity());

        repository.save(cart);

        return modelMapper.map(cartItem, ShoppingCartItemDto.class);
    }

    @Override
    public void deleteItemById(long id) {
        ShoppingCart cart = repository.findByUserId(loggedInUserService.getUserId())
                .orElse(null);

        Set<ShoppingCartItem> cartItems = cart.getItems().stream()
                .filter(i -> i.getId() != id)
                .collect(Collectors.toSet());

        cart.setItems(cartItems);

        repository.save(cart);
    }
}
