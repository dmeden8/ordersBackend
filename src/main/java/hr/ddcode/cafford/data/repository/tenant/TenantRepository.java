package hr.ddcode.cafford.data.repository.tenant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.ddcode.cafford.data.entity.Tenant;

@Repository
public interface TenantRepository extends JpaRepository <Tenant, Long> {

}
