package net.groupseven.onlinemarketg7backend.role.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
 public class RoleDto {
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String authority;

    public RoleDto(String authority){
        this.setAuthority(authority);
    }
}
