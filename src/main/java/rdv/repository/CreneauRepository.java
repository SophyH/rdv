package rdv.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rdv.model.Creneau;

public interface CreneauRepository extends JpaRepository<Creneau, Integer>{
	
	@Query("select distinct c from Creneau c left join fetch c.disponibilite where c.heureDebut=:hdebut")
	public Optional<Creneau> findByHeureDebutWithDisponibilite(@Param("hdebut") Date hdebut);

	@Query("select distinct c from Creneau c left join fetch c.consultation where c.id=:id")
	public Optional<Creneau> findByIdWithConsultation(@Param("id") Integer id);
	
}
