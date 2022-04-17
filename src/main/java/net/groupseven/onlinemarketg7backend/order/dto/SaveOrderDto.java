package net.groupseven.onlinemarketg7backend.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaveOrderDto {
    private long id;

    @NotNull
    @NotEmpty
    private List<SaveOrderItemDto> orderItems;

    private long shippingAddressId;

    private long paymentMethodId;
}
