package net.groupseven.onlinemarketg7backend.address.dto;

import com.online.market.address.model.AddressType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaveAddressDto {
    @NotBlank
    private String street;

    @NotBlank
    private String city;

    private String state;

    @NotBlank
    private String zipCode;

    private AddressType type;

    private long userId;
}
