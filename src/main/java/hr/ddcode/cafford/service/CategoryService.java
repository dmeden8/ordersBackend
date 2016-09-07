package hr.ddcode.cafford.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.ddcode.cafford.business.component.category.CategoryManager;
import hr.ddcode.cafford.data.dto.request.CategoryTenantDto;
import hr.ddcode.cafford.data.dto.response.CategoryDto;
import hr.ddcode.cafford.data.dto.response.CategoryMobileDto;

@RestController
@RequestMapping("/category")
@Transactional
public class CategoryService {
	
	@Autowired
    private CategoryManager categoryManager;
	
	@RequestMapping(value = "/children", 
    		method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<CategoryDto> getCategoriesByParentName(@RequestBody CategoryTenantDto categoryTenantDto) {				
		return categoryManager.getCategoriesByParentId(categoryTenantDto);
    }
	
	
	@RequestMapping(value = "/all", 
    		method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<CategoryMobileDto> getAllCategories(@RequestBody Long tenantId) {				
		return categoryManager.getAllCategories(tenantId);
    }

}
