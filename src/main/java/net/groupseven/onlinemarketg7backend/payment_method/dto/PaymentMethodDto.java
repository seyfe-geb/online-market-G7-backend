package net.groupseven.onlinemarketg7backend.payment_method.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.groupseven.onlinemarketg7backend.payment_method.model.PaymentMethodType;
import net.groupseven.onlinemarketg7backend.user.dto.UserDto;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
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

