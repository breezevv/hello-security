package example.hellosecurity.repository;

import example.hellosecurity.domain.RoleAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleAuthorityRepository extends JpaRepository<RoleAuthority, Long>, CrudRepository<RoleAuthority, Long> {

    List<RoleAuthority> findRoleAuthoritiesByRoleIdIn(List<Long> roleIds);
}