package hr.ddcode.cafford.business.component.item;

import java.util.List;

import hr.ddcode.cafford.data.dto.request.CategoryTenantDto;
import hr.ddcode.cafford.data.dto.request.ItemDiscountDto;
import hr.ddcode.cafford.data.dto.response.ItemDto;

public interface ItemManager {
	
	List<ItemDto> getItemsByTenant(CategoryTenantDto categoryItemDto);
	
	ItemDto getItemById (Long itemId);
	
	Long setItemDiscount(ItemDiscountDto itemDiscountDto);
	
	List<ItemDto> getDiscountItems(Long tenantId);

}
