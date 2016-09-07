package hr.ddcode.cafford.business.component.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import hr.ddcode.cafford.data.dto.request.OrderFilterDto;
import hr.ddcode.cafford.data.dto.response.OrderDto;

@Component
public class OrderHandler extends TextWebSocketHandler {
	
	@Autowired
    private OrderManager orderManager;
	
	@Autowired
	ObjectMapper objectMapper;

    WebSocketSession session;

    public void sendNewOrders(OrderFilterDto orderFilterDto) {
        System.out.println("Trying to send");
        if (session != null && session.isOpen()) {
            try {
                System.out.println("Now sending");
                List<OrderDto> ordersDto = orderManager.getFilteredOrders(orderFilterDto);
                session.sendMessage(new TextMessage(objectMapper.writeValueAsString(ordersDto)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Don't have open session to send");
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("Connection established");
        this.session = session;
    }

}
