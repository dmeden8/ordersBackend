package hr.ddcode.cafford.business.component.userAccount;

import java.util.List;

import hr.ddcode.cafford.data.dto.request.UserFilterDto;
import hr.ddcode.cafford.data.dto.request.UserRegisterDto;
import hr.ddcode.cafford.data.dto.request.UserStatusDto;
import hr.ddcode.cafford.data.dto.response.UserAccountDto;
import hr.ddcode.cafford.data.type.UserStatus;

public interface UserAccountManager {
			
	Long registerUser(UserRegisterDto userRegisterDto);
	
	void delete(Long id);
	
	List<UserAccountDto> getFilteredUsers(UserFilterDto userFilterDto);
	
	UserAccountDto getUserById(Long userId);
	
	String changeStatus(UserStatusDto userStatusDto);
	
	Long countByStatus(UserStatus userStatus);

}
