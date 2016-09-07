package hr.ddcode.cafford.data.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.ddcode.cafford.data.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
