package example.hellosecurity.repository;

import example.hellosecurity.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long>, CrudRepository<Authority, Long> {
}