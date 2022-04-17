package net.groupseven.onlinemarketg7backend.order.service;

import com.online.market.order.dto.OrderDto;
import com.online.market.order.dto.SaveOrderDto;
import com.online.market.order.model.OrderStatus;

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
