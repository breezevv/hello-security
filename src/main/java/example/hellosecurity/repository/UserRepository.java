package example.hellosecurity.repository;

import example.hellosecurity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long>, CrudRepository<User, Long> {

    User findUserByUsername(String username);
}