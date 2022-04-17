package net.groupseven.onlinemarketg7backend.order.service;


import net.groupseven.onlinemarketg7backend.order.dto.OrderItemDto;
import net.groupseven.onlinemarketg7backend.order.dto.SaveOrderItemDto;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDto> findAll();

    OrderItemDto findById(long id);

    SaveOrderItemDto add(SaveOrderItemDto dto);

    void deleteById(long id);
}
