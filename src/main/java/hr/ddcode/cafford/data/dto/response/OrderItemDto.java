package hr.ddcode.cafford.data.dto.response;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
	
    private String name;
	
	private String price;
	
	private Integer amount;
	
	private Long id;
	
	private String measure;
	
	private String categoryName;
	
	public void setPrice(BigDecimal price, Long discount) {		
		this.price = price.multiply(new BigDecimal((100-discount)/100.0)).setScale(2, RoundingMode.HALF_UP).toPlainString();					
	}

}
