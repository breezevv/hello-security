package example.hellosecurity.repository;

import example.hellosecurity.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends JpaRepository<Role, Long>, CrudRepository<Role, Long> {
}