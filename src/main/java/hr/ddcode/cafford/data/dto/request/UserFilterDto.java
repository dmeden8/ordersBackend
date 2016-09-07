package hr.ddcode.cafford.data.dto.request;

import hr.ddcode.cafford.data.type.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFilterDto {
	
	private Long tenantId;
	
	private UserStatus status;

}
