package net.groupseven.onlinemarketg7backend.order.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.groupseven.onlinemarketg7backend.product.dto.ProductDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDto {

    private long id;

    private int quantity;

    private ProductDto product;

    @JsonBackReference
    private OrderDto order;
}
