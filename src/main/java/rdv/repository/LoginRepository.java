package rdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rdv.model.Login;

public interface LoginRepository extends JpaRepository<Login, String> {

//	@Query("select l from Login l left join fetch l.role where l.username=:username")
//	Optional<Login> findByUserNameWithUserRole(String username);
}
