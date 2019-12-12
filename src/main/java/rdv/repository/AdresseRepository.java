package rdv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rdv.model.Adresse;

public interface AdresseRepository extends JpaRepository<Adresse, Integer> {

	@Query("select a from Adresse a left join fetch a.praticienAdresses pa left join fetch pa.key.praticien p where p.nom = :nom")
	public List<Adresse> findAllWithPraticienByNomPraticien(@Param("nom") String nom);

	@Query("select a from Adresse a left join fetch a.praticienAdresses pa left join fetch pa.key.praticien")
	public List<Adresse> findAllWithPraticien();

	@Query("select distinct a from Adresse a left join fetch a.praticienAdresses pa left join fetch pa.key.praticien p where p.id = :id")
	public List<Adresse> findAllByIdPraticien(@Param("id") Integer id);

}
