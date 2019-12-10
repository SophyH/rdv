package rdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rdv.model.Login;

public interface LoginRepository extends JpaRepository<Login, String> {

}
