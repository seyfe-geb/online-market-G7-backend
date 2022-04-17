package net.groupseven.onlinemarketg7backend.order.dto;


import com.sun.istack.NotNull;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaveOrderDto {
    private long id;

    @NotNull
    @NotEmpty
    private List<SaveOrderItemDto> orderItems;

    private long shippingAddressId;

    private long paymentMethodId;
}
