package net.groupseven.onlinemarketg7backend.auth.dto;


import lombok.*;
import net.groupseven.onlinemarketg7backend.role.model.Role;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthResponseDto {

    private String id;

    private String fname;

    private String lname;

    private String token;

    private List<Role> authorities;
}
