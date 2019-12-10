package rdv.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rdv.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

	// Je veux chercher les usernames en fonction de leur role: 
	//admin, patient, praticien.
	@Query("select u from UserRole u left join fetch u.username where u.role=:uppercase(role)")
	public List<UserRole> findUsernameByRole(@Param("role") String role);

}
