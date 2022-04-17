package net.groupseven.onlinemarketg7backend.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaveOrderItemDto {
    private long id;

    private int quantity;

    private long productId;

    private long orderId;
}
