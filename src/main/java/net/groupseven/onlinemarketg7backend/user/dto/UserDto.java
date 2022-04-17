package net.groupseven.onlinemarketg7backend.user.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.groupseven.onlinemarketg7backend.role.dto.RoleDto;

import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto extends LightUserDto{

    private Date createdAt;

    private Date modifiedAt;

    private boolean isApprovedSeller;

    private Set<RoleDto> authorities;
}
