package net.groupseven.onlinemarketg7backend.order.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.online.market.product.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemDto {

    private long id;

    private int quantity;

    private ProductDto product;

    @JsonBackReference
    private OrderDto order;
}
