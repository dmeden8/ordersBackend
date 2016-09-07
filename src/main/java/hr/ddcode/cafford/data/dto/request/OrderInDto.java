package hr.ddcode.cafford.data.dto.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInDto {
				
	private String userName;
	
	private Long userId;
	
	private Long tenantId;
	
	private Long paymentTypeId;
	
	private List<OrderItemInDto> itemList;
	
}

	
