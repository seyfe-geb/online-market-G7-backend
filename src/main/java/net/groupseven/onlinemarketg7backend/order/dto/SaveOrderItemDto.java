package net.groupseven.onlinemarketg7backend.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveOrderItemDto {
    private long id;

    private int quantity;

    private long productId;

    private long orderId;
}
