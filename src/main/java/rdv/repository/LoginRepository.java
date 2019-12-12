package rdv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rdv.model.Login;

public interface LoginRepository extends JpaRepository<Login, String> {
	@Query("select l from Login l where l.username=:username")
	Optional<Login> findByUserNameWithUserRole(String username);
}
