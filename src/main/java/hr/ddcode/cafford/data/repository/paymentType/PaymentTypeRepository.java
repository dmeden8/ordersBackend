package hr.ddcode.cafford.data.repository.paymentType;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.ddcode.cafford.data.entity.PaymentType;
import hr.ddcode.cafford.data.entity.Tenant;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {
	
	List<PaymentType> findByTenant(Tenant tenant);

}
