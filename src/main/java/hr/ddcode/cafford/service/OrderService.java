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

import hr.ddcode.cafford.business.component.order.OrderManager;
import hr.ddcode.cafford.data.dto.request.OrderFilterDto;
import hr.ddcode.cafford.data.dto.request.OrderInDto;
import hr.ddcode.cafford.data.dto.request.OrderStatusDto;
import hr.ddcode.cafford.data.dto.response.OrderDto;
import hr.ddcode.cafford.data.dto.response.OrderItemDto;
import hr.ddcode.cafford.data.dto.response.PaymentTypeDto;

@RestController
@RequestMapping("/order")
@Transactional
public class OrderService {
	
	@Autowired
    private OrderManager orderManager;
	
	@RequestMapping(value = "/new", 
    		method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Long saveOrUpdate(@RequestBody OrderInDto orderInDto) {		
		return orderManager.saveNewOrder(orderInDto);		
    }
	
	
	@RequestMapping(value = "/list", 
    		method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<OrderDto> getFilteredOrders(@RequestBody OrderFilterDto orderFilterDto) {
		return orderManager.getFilteredOrders(orderFilterDto);				
    }
	
	
	@RequestMapping(value = "/items", 
    		method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<OrderItemDto> getOrderItemsById(@RequestBody Long orderId) {
		return orderManager.getOrderItemsById(orderId);				
    }
	
	
	@RequestMapping(value = "/details", 
    		method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	public OrderDto getOrderById(@RequestBody Long orderId) {
		return orderManager.getOrderById(orderId);									
    }
	
	
	@RequestMapping(value = "/changestatus", 
    		method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String changeStatus(@RequestBody OrderStatusDto orderStatusDto) {
		return orderManager.changeStatus(orderStatusDto);							
    }
	
	@RequestMapping(value = "/paymentlist", 
    		method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<PaymentTypeDto> getPaymentTypes(@RequestBody Long tenantId) {
		return orderManager.getPaymentTypes(tenantId);							
    }
	
	
	@RequestMapping(value = "/countstatus", 
    		method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long countByStatus(@RequestBody OrderStatusDto orderStatusDto) {
		return orderManager.countByStatus(orderStatusDto.getStatus());						
    }
	
	
	@RequestMapping(value = "/test", 
    		method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void testPerformanceOrder() {
		orderManager.testPerformanceOrder();							
    }
	

}
