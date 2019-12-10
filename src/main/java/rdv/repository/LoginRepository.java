package rdv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rdv.model.Login;

public interface LoginRepository extends JpaRepository<Login, String> {

	// Je veux chercher le role (admin/patient/praticien) en fonction du username
	@Query("select l from Login l left join fetch l.role where l.username=:username")
	Optional<Login> findByUserNameWithUserRole(@Param("username") String username);

}
