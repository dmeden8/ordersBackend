package hr.ddcode.cafford.data.dto.response;

import java.util.List;

import hr.ddcode.cafford.data.type.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
	
	 private Long id;
	 
	 private List<Long> tenantIds;
	 
	 private String name;
	 
	 private UserStatus status;

}
