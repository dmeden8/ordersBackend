package hr.ddcode.cafford.business.component.order;

import java.util.List;

import hr.ddcode.cafford.data.dto.request.OrderFilterDto;
import hr.ddcode.cafford.data.dto.request.OrderInDto;
import hr.ddcode.cafford.data.dto.request.OrderStatusDto;
import hr.ddcode.cafford.data.dto.response.OrderDto;
import hr.ddcode.cafford.data.dto.response.OrderItemDto;
import hr.ddcode.cafford.data.dto.response.PaymentTypeDto;
import hr.ddcode.cafford.data.type.OrderStatus;

public interface OrderManager {
			
	Long saveNewOrder(OrderInDto orderInDto);
		
	List<OrderItemDto> getOrderItemsById(Long orderId);
	
	OrderDto getOrderById(Long orderId);
	
	String changeStatus(OrderStatusDto orderStatusDto);
	
	void testPerformanceOrder();
	
	List<OrderDto> getFilteredOrders(OrderFilterDto orderFilterDto);
	
	Long countByStatus(OrderStatus orderStatus);
	
	List<PaymentTypeDto> getPaymentTypes(Long tenantId);

}
