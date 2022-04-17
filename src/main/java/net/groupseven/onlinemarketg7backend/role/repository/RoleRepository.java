package net.groupseven.onlinemarketg7backend.role.repository;

import com.online.market.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByAuthority(String authority);
    boolean existsByAuthority(String name);
}