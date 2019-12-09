package rdv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rdv.model.Personne;
import rdv.model.Praticien;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {

	@Query("select distinct p from Praticien p where p.nom = :nom")
	public Optional<Praticien> findByNom(String nom);

	@Query("select distinct p from Praticien p left join fetch p.adresses left join fetch p.specialites")
	public Optional<Praticien> findWithAdressesAndSpecialites();

	@Query("select distinct p from Praticien p left foin fetch p.adresses.ville where p.adresses.ville = :ville")
	public List<Praticien> findAllByVille(String ville);

}
