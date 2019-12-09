package rdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rdv.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{

}
