package hr.ddcode.cafford.data.repository.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.ddcode.cafford.data.entity.Category;
import hr.ddcode.cafford.data.entity.Item;
import hr.ddcode.cafford.data.entity.Tenant;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	
	List<Item> findByTenantOrderByName(Tenant tenant);
	
	List<Item> findByTenantAndCategoryOrderByName(Tenant tenant, Category category);
	
	List<Item> findByTenantAndDiscountNotOrderByName(Tenant tenant, Long discount);

}
