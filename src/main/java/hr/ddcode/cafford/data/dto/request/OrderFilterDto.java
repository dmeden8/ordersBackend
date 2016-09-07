package hr.ddcode.cafford.data.dto.request;

import hr.ddcode.cafford.data.type.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderFilterDto {
	
	private Long userId;
	
	private Long tenantId;
	
	private OrderStatus status;

}
