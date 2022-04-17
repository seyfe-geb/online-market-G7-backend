package net.groupseven.onlinemarketg7backend.order.service;

import com.online.market.order.dto.OrderItemDto;
import com.online.market.order.dto.SaveOrderItemDto;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDto> findAll();

    OrderItemDto findById(long id);

    SaveOrderItemDto add(SaveOrderItemDto dto);

    void deleteById(long id);
}
