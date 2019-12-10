package rdv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rdv.model.Consultation;
import rdv.model.ConsultationPk;

public interface ConsultationRepository extends JpaRepository<Consultation, ConsultationPk>{

	@Query("select distinct c from Consultation c left join fetch c.creneau where c.key=:key")
	public Optional<Consultation> findByIdWithCreneau(@Param("key") ConsultationPk key);
	
	@Query("select c from Consultation c where c.numeroConsultation=:numeroConsultation")
	public Optional<Consultation> findByNumeroConsultation(@Param("numeroConsultation") Integer numeroConsultation);
	
	@Query("delete from Consultation c where c.numeroConsultation=:numeroConsultation")
	public Optional<Consultation> deleteByNumeroConsultation(@Param("numeroConsultation") Integer numeroConsultation);
}
