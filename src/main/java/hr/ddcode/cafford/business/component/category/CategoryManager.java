package hr.ddcode.cafford.business.component.category;

import java.util.List;

import hr.ddcode.cafford.data.dto.request.CategoryTenantDto;
import hr.ddcode.cafford.data.dto.response.CategoryDto;
import hr.ddcode.cafford.data.dto.response.CategoryMobileDto;

public interface CategoryManager {
	
	List<CategoryDto> getCategoriesByParentId(CategoryTenantDto categoryTenantDto);
	
	List<CategoryMobileDto> getAllCategories(Long tenantId);

}
