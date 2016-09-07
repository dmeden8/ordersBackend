package hr.ddcode.cafford.data.dto.response;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import hr.ddcode.cafford.data.type.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties({"dtf"})
public class OrderDto {
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
	
	private String creationTime;
	
    private OrderStatus status;
	
	private String totalPrice;
	
	private String userName;
	
	private String paymentType;
	
	private Long id;
	

	public void setCreationTime(ZonedDateTime creationTime) {
		this.creationTime = creationTime.format(dtf);
	}
	
	public void setTotalPrice(BigDecimal price) {		
		this.totalPrice = price.setScale(2, RoundingMode.HALF_UP).toPlainString();					
	}
	

}

	
