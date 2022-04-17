package net.groupseven.onlinemarketg7backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class CreateUserDto extends CreateLightUserDto {
    private Set<String> roles;
}
