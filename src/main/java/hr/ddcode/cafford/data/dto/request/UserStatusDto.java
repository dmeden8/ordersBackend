package hr.ddcode.cafford.data.dto.request;

import hr.ddcode.cafford.data.type.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserStatusDto {
	
	private Long userId;
	
	private UserStatus status;

}
