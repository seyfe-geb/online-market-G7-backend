package net.groupseven.onlinemarketg7backend.payment_method.dto;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.groupseven.onlinemarketg7backend.payment_method.model.PaymentMethodType;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SavePaymentMethodDto {

    private String name;

    @NotNull
    private PaymentMethodType type;

    @NotBlank
    @NotNull
    private String number;

    @NotNull
    @Digits(integer = 5, fraction = 0)
    private int csv;

    private boolean isDefault;

    private long billingAddressId;

    private long userId;
}
