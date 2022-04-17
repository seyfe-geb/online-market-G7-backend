package net.groupseven.onlinemarketg7backend.payment_method.dto;

import com.online.market.payment_method.model.PaymentMethodType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SavePaymentMethodDto {
    @NotBlank
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
