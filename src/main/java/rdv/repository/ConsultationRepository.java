package rdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rdv.model.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Integer>{

}
