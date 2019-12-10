package rdv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rdv.model.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Integer>{

	@Query("select distinct c from Consultation c left join fetch c.creneau where c.id=:id")
	public Optional<Consultation> findByIdWithCreneau(@Param("id") Integer id);
	
}
