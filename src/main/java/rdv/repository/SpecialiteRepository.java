package rdv.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rdv.model.Specialite;

public interface SpecialiteRepository extends JpaRepository<Specialite, Integer> {
	
	public Optional<Specialite> findBySpecialite(String specialite);
	
	public List<Specialite> findByDuree(Date duree);
	
	@Query("select distinct s from Specialite s left join fetch s.praticien")
	public List<Specialite> findAllWithPraticien();

}
