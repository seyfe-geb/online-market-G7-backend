package net.groupseven.onlinemarketg7backend.order.service;


import net.groupseven.onlinemarketg7backend.order.dto.OrderDto;
import net.groupseven.onlinemarketg7backend.order.dto.SaveOrderDto;
import net.groupseven.onlinemarketg7backend.order.model.OrderStatus;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface OrderService {
    List<OrderDto> findAll();
    List<OrderDto> findAllByUserId();
    List<OrderDto> findSellerOrders();

    OrderDto findById(long id);

    OrderDto add(SaveOrderDto dto) throws ValidationException;

    OrderDto changeStatusById(long id, OrderStatus status) throws ValidationException;
}
