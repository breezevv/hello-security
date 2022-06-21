package example.hellosecurity.repository;

import example.hellosecurity.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>, CrudRepository<UserRole, Long> {

    List<UserRole> findUserRolesByUserId(Long userId);
}