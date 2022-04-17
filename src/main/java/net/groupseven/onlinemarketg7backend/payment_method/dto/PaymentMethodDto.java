package net.groupseven.onlinemarketg7backend.payment_method.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.online.market.payment_method.model.PaymentMethodType;
import com.online.market.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentMethodDto {

    private long id;

    @NotBlank
    private String name;

    @NotNull
    private PaymentMethodType type;

    @NotBlank
    @NotNull
    private String number;

    private boolean isDefault;

    private long billingAddressId;

    @JsonBackReference
    private UserDto user;
}

