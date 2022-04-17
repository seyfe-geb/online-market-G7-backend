package net.groupseven.onlinemarketg7backend.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthRequestDto {

    @NotNull
    private String username;

   /* @Email
    private String email;*/

    @NotNull
    private String password;

}
