package net.groupseven.onlinemarketg7backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LightUserDto {
    private long id;

    @NotNull
    @Size(max = 50)
    private String fname;

    @NotNull
    @Size(max = 50)
    private String lname;

    @NotNull
    @Size(max = 50)
    @Email
    private String email;
}
