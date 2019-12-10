package rdv.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rdv.model.Creneau;
import rdv.model.Disponibilite;
import rdv.model.Jour;

public interface DisponibiliteRepository extends JpaRepository<Disponibilite, Integer> {

	public Optional<Disponibilite> findByJour(Jour jour);
	
	// trouver dispo et son praticien
	@Query("select distinct d from Disponibilite d left join fetch d.praticien where d.id=:id")
	public Optional<Disponibilite> findWithPraticien(@Param("id") Integer id);
	
	
	// trouver les creneaux dune dispo
	@Query("select d from Disponibilite d left join fetch d.creneaux where d.hdebut=:heureDebut")
	public Optional<Disponibilite> findByHeureDebutWithCreneau(@Param("heureDebut") Date heureDebut );
	
	
	
	
}
