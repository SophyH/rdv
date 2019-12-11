package rdv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rdv.model.Consultation;
import rdv.model.ConsultationPk;

public interface ConsultationRepository extends JpaRepository<Consultation, ConsultationPk> {

	@Query("select distinct c from Consultation c left join fetch c.creneau where c.key=:key")
	public Optional<Consultation> findByIdWithCreneau(@Param("key") ConsultationPk key);

	@Query("select c from Consultation c where c.numeroConsultation=:numeroConsultation")
	public Optional<Consultation> findByNumeroConsultation(@Param("numeroConsultation") Integer numeroConsultation);

	@Query("delete from Consultation c where c.numeroConsultation=:numeroConsultation")
	public Optional<Consultation> deleteByNumeroConsultation(@Param("numeroConsultation") Integer numeroConsultation);

	@Query("select distinct c from Consultation left join fetch c.creneaux left join fetch c.key.patient p where p.id=:id")
	public List<Consultation> findAllByIdPatient(@Param("id") Integer id);

	@Query("select distinct c from Consultation left join fetch c.creneaux left join fetch c.key.praticien p where p.id=:id")
	public List<Consultation> findAllByIdPraticien(@Param("id") Integer id);

}
