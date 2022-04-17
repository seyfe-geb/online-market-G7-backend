package net.groupseven.onlinemarketg7backend.role.service;



import net.groupseven.onlinemarketg7backend.role.dto.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> findAll();
    RoleDto findById(long id);

    RoleDto add(RoleDto dto);
    RoleDto updateById(long id, RoleDto dto);
    void deleteById(long id);

    boolean existsByAuthority(String name);
}
