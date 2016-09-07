package hr.ddcode.cafford.business.component.item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hr.ddcode.cafford.data.dto.request.CategoryTenantDto;
import hr.ddcode.cafford.data.dto.request.ItemDiscountDto;
import hr.ddcode.cafford.data.dto.response.ItemDto;
import hr.ddcode.cafford.data.entity.Category;
import hr.ddcode.cafford.data.entity.Item;
import hr.ddcode.cafford.data.entity.Tenant;
import hr.ddcode.cafford.data.repository.category.CategoryRepository;
import hr.ddcode.cafford.data.repository.item.ItemRepository;
import hr.ddcode.cafford.data.repository.tenant.TenantRepository;

@Component
public class ItemManagerImpl implements ItemManager {
	
	@Autowired
    private ItemRepository itemRepo;
	
	@Autowired
    private TenantRepository tenantRepo;
	
	@Autowired
    private CategoryRepository categoryRepo;
	
	
	public List<ItemDto> getItemsByTenant(CategoryTenantDto categoryTenantDto) {
		
		Tenant tenant = tenantRepo.findOne(categoryTenantDto.getTenantId());
				
		List<Item> items = new ArrayList<Item>();
								
		if(categoryTenantDto.getCategoryId() == null) {
			items = itemRepo.findByTenantOrderByName(tenant);
		}
		else {
			Category category = categoryRepo.findOne(categoryTenantDto.getCategoryId());
			items = itemRepo.findByTenantAndCategoryOrderByName(tenant,category);
		}
		
		List<ItemDto> itemsDto = new ArrayList<ItemDto>();
		
		for (Item item : items) {
			ItemDto itemDto = new ItemDto();			
			itemDto.setName(item.getName());
			itemDto.setMpcPrice(item.getMpcPrice(),item.getDiscount());
			itemDto.setVpcPrice(item.getVpcPrice(),item.getDiscount());
			itemDto.setCategoryName(item.getCategory().getName());
			itemDto.setMeasure(item.getMeasure());
			itemDto.setExternalId(item.getExtrenalId());
			itemDto.setId(item.getId());
			itemDto.setCategoryId(item.getCategory().getId());
			
			itemsDto.add(itemDto);
		}
		
		return itemsDto;				
		
	}
	
	public ItemDto getItemById(Long itemId) {
		Item item = itemRepo.findOne(itemId);	
		
		ItemDto itemDto = new ItemDto();			
    	
		itemDto.setName(item.getName());
		itemDto.setMpcPrice(item.getMpcPrice(),item.getDiscount());
		itemDto.setVpcPrice(item.getVpcPrice(),item.getDiscount());
		itemDto.setCategoryName(item.getCategory().getName());
		itemDto.setMeasure(item.getMeasure());
		itemDto.setExternalId(item.getExtrenalId());
		itemDto.setId(item.getId());
		itemDto.setCategoryId(item.getCategory().getId());
		itemDto.setDiscount(item.getDiscount());
		    			
		return itemDto;	
    }
	
	public Long setItemDiscount(ItemDiscountDto itemDiscountDto) {
		Item item = itemRepo.findOne(itemDiscountDto.getItemId());	
		
		item.setDiscount(Long.parseLong(itemDiscountDto.getDiscount()));		
		item = itemRepo.saveAndFlush(item);
									
		return item == null ? null : item.getId();		
	}
	
	public List<ItemDto> getDiscountItems(Long tenantId) {
		Tenant tenant = tenantRepo.findOne(tenantId);
		
		List<Item> items = itemRepo.findByTenantAndDiscountNotOrderByName(tenant, 0L );
		
		List<ItemDto> itemsDto = new ArrayList<ItemDto>();
		
		for (Item item : items) {
			ItemDto itemDto = new ItemDto();			
			itemDto.setName(item.getName());
			itemDto.setMpcPrice(item.getMpcPrice(),item.getDiscount());
			itemDto.setVpcPrice(item.getVpcPrice(),item.getDiscount());
			itemDto.setCategoryName(item.getCategory().getName());
			itemDto.setMeasure(item.getMeasure());
			itemDto.setExternalId(item.getExtrenalId());
			itemDto.setId(item.getId());
			itemDto.setCategoryId(item.getCategory().getId());
			itemDto.setDiscount(item.getDiscount());
			
			itemsDto.add(itemDto);
		}
		
		return itemsDto;
		
	}
	

}
