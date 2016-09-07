package hr.ddcode.cafford.data.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
	
    private String name;
	
	private Long categoryId;
	
	private String imageUrl;
	
	private Boolean hasChildren;

}
