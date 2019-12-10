package rdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rdv.model.PraticienAdresse;
import rdv.model.PraticienAdressePk;

public interface PraticienAdresseRepository extends JpaRepository<PraticienAdresse, PraticienAdressePk> {

}
