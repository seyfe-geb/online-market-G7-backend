package net.groupseven.onlinemarketg7backend.user.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import net.groupseven.onlinemarketg7backend.address.dto.AddressDto;
import net.groupseven.onlinemarketg7backend.payment_method.dto.PaymentMethodDto;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserProfileDto extends UserDto{
    private String username;

    @JsonManagedReference
    private List<AddressDto> addresses;

    @JsonManagedReference
    private List<PaymentMethodDto> paymentMethods;
}
