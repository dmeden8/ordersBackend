package hr.ddcode.cafford.data.repository.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.ddcode.cafford.data.entity.Order;
import hr.ddcode.cafford.data.entity.Tenant;
import hr.ddcode.cafford.data.entity.UserAccount;
import hr.ddcode.cafford.data.type.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	List<Order> findByTenantOrderByCreationTimeDesc(Tenant tenant);
	
	List<Order> findByTenantAndStatusOrderByCreationTimeDesc(Tenant tenant, OrderStatus status);
	
	List<Order> findByTenantAndUserAccountOrderByCreationTimeDesc(Tenant tenant, UserAccount userAccount);
	
	List<Order> findByTenantAndUserAccountAndStatusOrderByCreationTimeDesc(Tenant tenant, UserAccount userAccount, OrderStatus status);
	
	Long countByStatus(OrderStatus status);
		
}
