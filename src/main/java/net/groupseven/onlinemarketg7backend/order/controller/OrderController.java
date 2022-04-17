package net.groupseven.onlinemarketg7backend.order.controller;


import lombok.RequiredArgsConstructor;
import net.groupseven.onlinemarketg7backend.order.dto.OrderDto;
import net.groupseven.onlinemarketg7backend.order.dto.SaveOrderDto;
import net.groupseven.onlinemarketg7backend.order.model.OrderStatus;
import net.groupseven.onlinemarketg7backend.order.service.OrderService;
import net.groupseven.onlinemarketg7backend.role.model.Role;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name = "Orders")
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @GetMapping
    //@PreAuthorize("hasRole('" + Role.ADMIN + "')")
    public List<OrderDto> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public OrderDto get(@PathVariable("id") long id){
        return service.findById(id);
    }

    @PostMapping
    public OrderDto add(@RequestBody SaveOrderDto dto) throws Exception {
        return service.add(dto);
    }

    @PutMapping("/change-order-status/{id}/{status}")
    public OrderDto changeOrderStatus(@PathVariable("id") long id, @PathVariable("status") OrderStatus status) throws Exception {
        return service.changeStatusById(id, status);
    }

    @GetMapping("/order-history")
    public List<OrderDto> getOrderHistory(){
        return service.findAllByUserId();
    }

    @GetMapping("/seller-orders")
    public List<OrderDto> getSellerOrders(){
        return service.findSellerOrders();
    }

}
