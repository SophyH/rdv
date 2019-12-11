package rdv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rdv.model.PraticienAdresse;
import rdv.model.PraticienAdressePk;

public interface PraticienAdresseRepository extends JpaRepository<PraticienAdresse, PraticienAdressePk> {

	@Query("select distinct pa from PraticienAdresse pa left join fetch pa.key.praticien p where p.id=:id")
	public List<PraticienAdresse> findAllByIdPraticient(@Param("id") Integer id);

}
