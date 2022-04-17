package net.groupseven.onlinemarketg7backend.user.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.online.market.address.dto.AddressDto;
import com.online.market.payment_method.dto.PaymentMethodDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserProfileDto extends UserDto{
    private String username;

    @JsonManagedReference
    private List<AddressDto> addresses;

    @JsonManagedReference
    private List<PaymentMethodDto> paymentMethods;
}
