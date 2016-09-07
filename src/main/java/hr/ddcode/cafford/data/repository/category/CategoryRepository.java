package hr.ddcode.cafford.data.repository.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.ddcode.cafford.data.entity.Category;
import hr.ddcode.cafford.data.entity.Tenant;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Category findById(Long Id);
	
	List<Category> findByTenant(Tenant tenant);
	
}
