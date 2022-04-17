package net.groupseven.onlinemarketg7backend.shopping_cart.controller;


import lombok.RequiredArgsConstructor;
import net.groupseven.onlinemarketg7backend.shopping_cart.dto.*;
import net.groupseven.onlinemarketg7backend.shopping_cart.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name = "Shopping Cart")
@RestController
@RequestMapping(path = "shopping-cart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService service;

    @GetMapping
    public ShoppingCartDto get() {
        return service.findCart();
    }

    @PostMapping
    public ShoppingCartDto addItems(@RequestBody SaveShoppingCartDto dto){
        return service.updateCart(dto);
    }

    @GetMapping("/items")
    public List<ShoppingCartItemDto> getAll() {
        return service.findAllItems();
    }

    @GetMapping("/item/{id}")
    public ShoppingCartItemDto getItem(@PathVariable long id) {
        return service.findItemById(id);
    }

    @PostMapping("/item")
    public ShoppingCartItemDto addItem(@RequestBody CreateShoppingCartItemDto dto){
        return service.addItem(dto);
    }

    @PutMapping("/item/{id}")
    public ShoppingCartItemDto updateItem(@PathVariable long id, @RequestBody UpdateShoppingCartItemDto dto){
        return service.updateItemById(id, dto);
    }

    @DeleteMapping("/item/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteItem(@PathVariable long id){
        service.deleteItemById(id);
    }
}
