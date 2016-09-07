package hr.ddcode.cafford.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.ddcode.cafford.business.component.item.ItemManager;
import hr.ddcode.cafford.data.dto.request.CategoryTenantDto;
import hr.ddcode.cafford.data.dto.request.ItemDiscountDto;
import hr.ddcode.cafford.data.dto.response.ItemDto;
import hr.ddcode.cafford.data.entity.Item;

@RestController
@RequestMapping("/item")
@Transactional
public class ItemService {
		
	@Autowired
    private ItemManager itemManager;
		
	@RequestMapping(value = "/new", 
    		method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long saveOrUpdate(@RequestBody Item item) {		
		return null;
    }
		
	@RequestMapping(value = "/list", 
    		method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ItemDto> getItemsByTenant(@RequestBody CategoryTenantDto categoryItemDto) {				
		return itemManager.getItemsByTenant(categoryItemDto);		
    }
	
	@RequestMapping(value = "/details", 
    		method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ItemDto getItemById(@RequestBody Long itemId) {
    	return itemManager.getItemById(itemId);  			
    }
	
	@RequestMapping(value = "/discount", 
    		method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Long setItemDiscount(@RequestBody ItemDiscountDto itemDiscountDto) {
	    return itemManager.setItemDiscount(itemDiscountDto);  	
    }
	
	@RequestMapping(value = "/discountlist", 
    		method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<ItemDto> getDiscountItems(@RequestBody Long tenantId) {
	    return itemManager.getDiscountItems(tenantId);  	
    }


}

