package hr.ddcode.cafford.data.repository.userAccount;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hr.ddcode.cafford.data.entity.Tenant;
import hr.ddcode.cafford.data.entity.UserAccount;
import hr.ddcode.cafford.data.type.UserStatus;;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
	
	UserAccount findByUsername(String username);
	
	@Query("SELECT ua FROM UserAccount ua INNER JOIN ua.tenants t WHERE t IN (:tenants) ORDER BY ua.registerTime DESC")
	public List<UserAccount> findByTenant(@Param("tenants") Set<Tenant> tenant);
	
	@Query("SELECT ua FROM UserAccount ua INNER JOIN ua.tenants t WHERE t IN (:tenants) AND ua.status = :status ORDER BY ua.registerTime DESC")
	public List<UserAccount> findByTenantAndStatus(@Param("tenants") Set<Tenant> tenant, @Param("status") UserStatus userStatus);
	
	Long countByStatus(UserStatus status);
	
}
