package net.groupseven.onlinemarketg7backend.address.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.online.market.address.model.AddressType;
import com.online.market.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDto {
    private long id;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    private String state;

    @NotBlank
    private String zipCode;

    private AddressType type;

    @JsonBackReference
    private UserDto user;
}
