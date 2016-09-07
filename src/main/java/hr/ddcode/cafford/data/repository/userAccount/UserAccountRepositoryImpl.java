package hr.ddcode.cafford.data.repository.userAccount;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import hr.ddcode.cafford.data.entity.UserAccount;

public class UserAccountRepositoryImpl implements UserAccountRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<UserAccount> findByTenantId(Long tenantId) {
		String query = "select ua from caffe_orders.user_account ua, caffe_orders.user_tenant ut where ua.id = ut.user_account_id and ut.tenant_id = ?tenantId";		
		return (List<UserAccount>)em.createQuery(query).setParameter("tenantId", tenantId).getResultList();
	}
	

}
