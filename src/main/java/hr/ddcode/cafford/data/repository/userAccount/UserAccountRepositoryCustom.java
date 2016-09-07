package hr.ddcode.cafford.data.repository.userAccount;

import java.util.List;

import hr.ddcode.cafford.data.entity.UserAccount;

public interface UserAccountRepositoryCustom {
	
	List<UserAccount> findByTenantId(Long tenantId);

}
