package net.groupseven.onlinemarketg7backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateLightUserDto {
    @NotBlank
    private String username;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    @Email
    private String email;

    @NotBlank
    private String password;
    @NotBlank
    private String rePassword;
}
