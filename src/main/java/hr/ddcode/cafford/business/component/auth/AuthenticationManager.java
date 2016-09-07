package hr.ddcode.cafford.business.component.auth;

import org.springframework.security.core.Authentication;

import hr.ddcode.cafford.data.dto.response.LoginDto;

public interface AuthenticationManager {

	LoginDto getLoginData (Authentication auth);
	
}
