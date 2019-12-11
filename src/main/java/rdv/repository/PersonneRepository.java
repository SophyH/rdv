package rdv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rdv.model.Patient;
import rdv.model.Personne;
import rdv.model.Praticien;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {

	@Query("select p from Personne p where p.nom=:nom")
	public List<Personne> findAllByNom(@Param("nom") String nom);

	@Query("select p from Praticien p left join fetch p.login where p.id=:id")
	public Optional<Praticien> findPraticienByIdWithLogin(@Param("id") Integer id);

	@Query("select p from Patient p left join fetch p.login where p.id=:id")
	public Optional<Patient> findPatientByIdWithLogin(@Param("id") Integer id);

	@Query("select p from Praticien p")
	public List<Praticien> findAllPraticien();

	@Query("select p from Patient p left join fetch p.consultations where p.id=:id")
	public Optional<Patient> findPatientWithConsultations(@Param("id") Integer id);

	@Query("select p from Patient p")
	public List<Patient> findAllPatient();

	@Query("select p from Patient p left join fetch p.consultations")
	public List<Patient> findAllPatientWithAll();

	@Query("select distinct p from Praticien p where p.nom=:nom")
	public List<Praticien> findByNomPraticien(@Param("nom") String nom);

	@Query("select distinct p from Patient p where p.nom=:nom")
	public List<Patient> findByNomPatient(@Param("nom") String nom);

	@Query("select distinct p from Praticien p left join fetch p.consultations left join fetch p.specialites left join fetch "
			+ "p.disponibilites left join fetch p.praticienAdresses pp left join fetch pp.key.adresse")
	public List<Praticien> findAllPraticienWithAll();

	@Query("select distinct p from Praticien p left join fetch p.consultations left join fetch p.specialites left join fetch "
			+ "p.disponibilites left join fetch p.praticienAdresses pp left join fetch pp.key.adresse where p.nom=:nom")
	public List<Praticien> findAllPraticienWithAllByNom(@Param("nom") String nom);

	@Query("select distinct p from Praticien p left join fetch p.consultations left join fetch p.specialites left join fetch "
			+ "p.disponibilites left join fetch p.praticienAdresses pp left join fetch pp.key.adresse a where a.ville=:ville")
	public List<Praticien> findAllPraticienWithAllByVille(@Param("ville") String ville);

	@Query("select distinct p from Praticien p left join fetch p.consultations left join fetch p.specialites s left join fetch "
			+ "p.disponibilites left join fetch p.praticienAdresses pp left join fetch pp.key.adresse where s.specialite=:specialite")
	public List<Praticien> findAllPraticienWithAllBySpecialite(@Param("specialite") String specialite);

	@Query("select distinct p from Praticien p left join fetch p.consultations left join fetch p.specialites s left join fetch "
			+ "p.disponibilites left join fetch p.praticienAdresses pp left join fetch pp.key.adresse a where s.specialite=:specialite "
			+ "and a.ville = :ville")
	public List<Praticien> findAllPraticienWithAllBySpecialiteAndVille(@Param("specialite") String specialite,
			@Param("ville") String ville);

}
