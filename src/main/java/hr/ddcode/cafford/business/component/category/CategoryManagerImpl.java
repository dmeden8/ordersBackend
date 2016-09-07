package hr.ddcode.cafford.business.component.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hr.ddcode.cafford.data.dto.request.CategoryTenantDto;
import hr.ddcode.cafford.data.dto.response.CategoryDto;
import hr.ddcode.cafford.data.dto.response.CategoryMobileDto;
import hr.ddcode.cafford.data.entity.Category;
import hr.ddcode.cafford.data.entity.Tenant;
import hr.ddcode.cafford.data.repository.category.CategoryRepository;
import hr.ddcode.cafford.data.repository.tenant.TenantRepository;

@Component
public class CategoryManagerImpl implements CategoryManager {
	
	@Autowired
    private CategoryRepository categoryRepo;
	
	@Autowired
    private TenantRepository tenantRepo;
	
	
	public List<CategoryDto> getCategoriesByParentId(CategoryTenantDto categoryTenantDto) {
				
		Category categoryParent = categoryRepo.findById(categoryTenantDto.getCategoryId());
		
		List<CategoryDto> childCategoriesDto = new ArrayList<CategoryDto>();
		List<Category> childCategories = categoryParent.getChildren();
		
		for (Category category : childCategories) {
			
			CategoryDto categoryDtoChild = new CategoryDto();
			categoryDtoChild.setCategoryId(category.getId());
			categoryDtoChild.setName(category.getName());
			categoryDtoChild.setImageUrl(category.getImageUrl());
								
			if (category.getChildren().size() == 0) 
				categoryDtoChild.setHasChildren(false);			
			else 
				categoryDtoChild.setHasChildren(true);
			
			
			childCategoriesDto.add(categoryDtoChild);
		}
		
		return childCategoriesDto;
		
	}
	
	
	public List<CategoryMobileDto> getAllCategories(Long tenantId) {
		
		Tenant tenant = tenantRepo.findOne(tenantId);
		
		List<Category> categories = categoryRepo.findByTenant(tenant);
		
		List<CategoryMobileDto> childMobileCategoriesDto = new ArrayList<CategoryMobileDto>();
		
		for (Category category : categories) {
			
			CategoryMobileDto categoryMobileDto = new CategoryMobileDto();
									
			if (category.getParent() != null) {
				categoryMobileDto.setParent(category.getParent().getId());
				categoryMobileDto.setId(category.getId());
				categoryMobileDto.setName(category.getName());
				
				childMobileCategoriesDto.add(categoryMobileDto);
			}
																	
		}
		
		return childMobileCategoriesDto;
		
	}
	

}
