package example.hellosecurity.repository;

import example.hellosecurity.domain.RoleAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleAuthorityRepository extends JpaRepository<RoleAuthority, Long>, CrudRepository<RoleAuthority, Long> {
}