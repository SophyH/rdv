package rdv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rdv.model.Disponibilite;

public interface DisponibiliteRepository extends JpaRepository<Disponibilite, Integer> {

	@Query("select distinct d from Disponibilite d left join fetch d.praticien where d.id=:id")
	public Optional<Disponibilite> findByIdWithPraticien(@Param("id") Integer id);
}
