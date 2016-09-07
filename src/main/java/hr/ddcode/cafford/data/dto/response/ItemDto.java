package hr.ddcode.cafford.data.dto.response;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
	
	private String name;
	
	private String mpcPrice;
	
	private String vpcPrice;
	
	private String discount;
	
	private Boolean hasDiscount;
	
	private Long id;
	
	private Long externalId;
	
	private String measure;
	
	private String categoryName;
	
	private Long categoryId;
		
	public void setMpcPrice(BigDecimal price, Long discount) {		
		this.mpcPrice = price.multiply(new BigDecimal((100-discount)/100.0)).setScale(2, RoundingMode.HALF_UP).toPlainString();					
	}
	
	public void setVpcPrice(BigDecimal price, Long discount) {		
		this.vpcPrice = price.multiply(new BigDecimal((100-discount)/100.0)).setScale(2, RoundingMode.HALF_UP).toPlainString();					
	}
	
	public void setDiscount(Long discount) {
		this.discount = discount.toString() + " %";
		this.setHasDiscount(discount);
	}
	
	public void setHasDiscount(Long discount) {
		this.hasDiscount = (discount != 0);
	}
	
}
