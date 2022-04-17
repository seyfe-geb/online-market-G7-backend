package net.groupseven.onlinemarketg7backend.order.dto;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
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
