package hr.ddcode.cafford.business.component.order;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hr.ddcode.cafford.data.dto.request.OrderFilterDto;
import hr.ddcode.cafford.data.dto.request.OrderInDto;
import hr.ddcode.cafford.data.dto.request.OrderItemInDto;
import hr.ddcode.cafford.data.dto.request.OrderStatusDto;
import hr.ddcode.cafford.data.dto.response.OrderDto;
import hr.ddcode.cafford.data.dto.response.OrderItemDto;
import hr.ddcode.cafford.data.dto.response.PaymentTypeDto;
import hr.ddcode.cafford.data.entity.Item;
import hr.ddcode.cafford.data.entity.Order;
import hr.ddcode.cafford.data.entity.OrderItem;
import hr.ddcode.cafford.data.entity.PaymentType;
import hr.ddcode.cafford.data.entity.Tenant;
import hr.ddcode.cafford.data.entity.UserAccount;
import hr.ddcode.cafford.data.repository.item.ItemRepository;
import hr.ddcode.cafford.data.repository.order.OrderRepository;
import hr.ddcode.cafford.data.repository.paymentType.PaymentTypeRepository;
import hr.ddcode.cafford.data.repository.tenant.TenantRepository;
import hr.ddcode.cafford.data.repository.userAccount.UserAccountRepository;
import hr.ddcode.cafford.data.type.OrderStatus;

@Component
public class OrderManagerImpl implements OrderManager {
	
	@Autowired
    private OrderRepository orderRepo;
	
	@Autowired
    private PaymentTypeRepository payTypeRepo;
	
	@Autowired
    private UserAccountRepository userAccRepo;
	
	@Autowired
    private ItemRepository itemRepo;
	
	@Autowired
    private TenantRepository tenantRepo;
		
	public Long saveNewOrder(OrderInDto orderInDto) {
		
		Order order = new Order();
		
		Tenant tenant = tenantRepo.findOne(orderInDto.getTenantId());
		UserAccount userAccount = userAccRepo.findOne(orderInDto.getUserId());
		PaymentType paymentType = payTypeRepo.findOne(orderInDto.getPaymentTypeId());
		
		order.setTenant(tenant);
		order.setUserAccount(userAccount);
		order.setPaymentType(paymentType);
		
		order.setCreationTime(ZonedDateTime.now(ZoneId.of("Europe/Zagreb")));		
		order.setStatus(OrderStatus.N);
				
		double totalPrice = 0.0d;
		
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		
		for (OrderItemInDto orderItemInDto : orderInDto.getItemList()) {
			
			OrderItem orderItem = new OrderItem();
			
			Item item = itemRepo.findOne(orderItemInDto.getItemId());
			orderItem.setItem(item);
			orderItem.setAmount(orderItemInDto.getAmount());
			
			if (orderItem.getItem().getDiscount().longValue() > 0) {
				totalPrice += orderItem.getAmount() * item.getMpcPrice().doubleValue() * (100 - orderItem.getItem().getDiscount()) / 100.0;
			}
			else {
				totalPrice += orderItem.getAmount() * item.getMpcPrice().doubleValue();
			}
									
			orderItem.setOrder(order);
			
			orderItems.add(orderItem);
		}
				
		order.setOrderItems(orderItems);
		order.setTotalPrice(new BigDecimal(totalPrice));
		order = orderRepo.saveAndFlush(order);
				
		
		//websocket support
		/*
		UserTenantDto userTenantDto = new UserTenantDto();
		userTenantDto.setTenantId(order.getTenant().getId());
		userTenantDto.setUserId(order.getUserAccount().getId());
		orderHandler.sendNewOrders(userTenantDto);
		*/
					
		return order == null ? null : order.getId();
		
	}
	
	
    public List<OrderDto> getFilteredOrders(OrderFilterDto orderFilterDto) {
    	
    	Tenant tenant = (orderFilterDto.getTenantId() != null ? tenantRepo.findOne(orderFilterDto.getTenantId()) : null);
    	UserAccount userAccount = (orderFilterDto.getUserId() != null ? userAccRepo.findOne(orderFilterDto.getUserId()) : null);
    			
    	
		List<Order> orders = new ArrayList<Order>();
		
		if(orderFilterDto.getUserId() == null && orderFilterDto.getStatus() == null) {
			orders = orderRepo.findByTenantOrderByCreationTimeDesc(tenant);
		}
		else if (orderFilterDto.getUserId() == null && orderFilterDto.getStatus() != null) {
			orders = orderRepo.findByTenantAndStatusOrderByCreationTimeDesc(tenant,orderFilterDto.getStatus());
		}
		else if (orderFilterDto.getUserId() != null && orderFilterDto.getStatus() == null) {
			orders = orderRepo.findByTenantAndUserAccountOrderByCreationTimeDesc(tenant,userAccount);
		}
		else {
			orders = orderRepo.findByTenantAndUserAccountAndStatusOrderByCreationTimeDesc(tenant,userAccount,orderFilterDto.getStatus());
		}
				
		List<OrderDto> ordersDto = new ArrayList<OrderDto>();
				
		for (Order order : orders) {
			OrderDto orderDto = new OrderDto();			
			orderDto.setId(order.getId());
			orderDto.setCreationTime(order.getCreationTime());
			orderDto.setStatus(order.getStatus());
			orderDto.setTotalPrice(order.getTotalPrice());
			orderDto.setUserName(order.getUserAccount().getName());
			orderDto.setPaymentType(order.getPaymentType().getDescription());
			
			ordersDto.add(orderDto);						
		}
		
		return ordersDto;	
    }
    
	
    public List<OrderItemDto> getOrderItemsById(Long orderId) {
    	
    	Order order = orderRepo.findOne(orderId);
		List<OrderItemDto> orderItemsDto = new ArrayList<OrderItemDto>();
				
		for (OrderItem orderItem : order.getOrderItems()) {
			OrderItemDto orderItemDto = new OrderItemDto();	
			orderItemDto.setAmount(orderItem.getAmount());
			orderItemDto.setName(orderItem.getItem().getName());
			orderItemDto.setPrice(orderItem.getItem().getMpcPrice(), orderItem.getItem().getDiscount());
			orderItemDto.setId(orderItem.getItem().getId());
			orderItemDto.setCategoryName(orderItem.getItem().getCategory().getName());
			orderItemDto.setMeasure(orderItem.getItem().getMeasure());
			orderItemsDto.add(orderItemDto);
		}
		
		return orderItemsDto;	
    	
    }
    
   		    	
    public OrderDto getOrderById(Long orderId) {
    	
    	Order order = orderRepo.findOne(orderId);		
    	
    	OrderDto orderDto = new OrderDto();			
    	orderDto.setId(order.getId());
    	orderDto.setCreationTime(order.getCreationTime());
    	orderDto.setStatus(order.getStatus());
    	orderDto.setTotalPrice(order.getTotalPrice());
    	orderDto.setUserName(order.getUserAccount().getName());
    	orderDto.setPaymentType(order.getPaymentType().getDescription());
    	
    	return orderDto;
    	
    }
    
       	
    public String changeStatus(OrderStatusDto orderStatusDto) {
    	
    	Order order = orderRepo.findOne(orderStatusDto.getOrderId());		
		order.setStatus(orderStatusDto.getStatus());
		orderRepo.saveAndFlush(order);
		
		return order.getStatus().name();
    	
    }
    
    public Long countByStatus(OrderStatus orderStatus) {
    	return orderRepo.countByStatus(orderStatus);
    }
    
    public List<PaymentTypeDto> getPaymentTypes(Long tenantId) {
    	
    	Tenant tenant = tenantRepo.findOne(tenantId);
    	List<PaymentTypeDto> paymentTypeDtoList = new ArrayList<PaymentTypeDto>();
    	
    	for (PaymentType paymentType :  payTypeRepo.findByTenant(tenant)) {
    		PaymentTypeDto paymentTypeDto = new PaymentTypeDto();	
    		paymentTypeDto.setId(paymentType.getId());
    		paymentTypeDto.setDescription(paymentType.getDescription());
    		paymentTypeDtoList.add(paymentTypeDto);
    	}
    	
    	return paymentTypeDtoList;
    }
    
      
    public void testPerformanceOrder() {
		
		for (int i = 0; i < 1000 ; i++) {
			
			OrderInDto orderInDto = new OrderInDto();			
			
			orderInDto.setTenantId(1L);
						
			if (i%3 == 0) {
				orderInDto.setUserId(2L);
			}
			else if (i%3 == 1) {
				orderInDto.setUserId(3L);
			}
			else if (i%3 == 2) {
				orderInDto.setUserId(4L);
			}
			
			List<OrderItemInDto> orderItemsInDto = new ArrayList<OrderItemInDto>();
			
			OrderItemInDto orderItemInDto1 = new OrderItemInDto();
			orderItemInDto1.setAmount(2);
			orderItemInDto1.setItemId(1L);
			orderItemsInDto.add(orderItemInDto1);
			
			OrderItemInDto orderItemInDto2 = new OrderItemInDto();
			orderItemInDto2.setAmount(4);
			orderItemInDto2.setItemId(5L);
			orderItemsInDto.add(orderItemInDto2);
			
			OrderItemInDto orderItemInDto3 = new OrderItemInDto();
			orderItemInDto3.setAmount(1);
			orderItemInDto3.setItemId(10L);
			orderItemsInDto.add(orderItemInDto3);
			
			OrderItemInDto orderItemInDto4 = new OrderItemInDto();
			orderItemInDto4.setAmount(2);
			orderItemInDto4.setItemId(12L);
			orderItemsInDto.add(orderItemInDto4);
			
			OrderItemInDto orderItemInDto5 = new OrderItemInDto();
			orderItemInDto5.setAmount(3);
			orderItemInDto5.setItemId(7L);
			orderItemsInDto.add(orderItemInDto5);
						
			orderInDto.setItemList(orderItemsInDto);
			
			saveNewOrder(orderInDto);
			
			System.out.println("Order " + i + " saved");
			
		}
												

    }

}
