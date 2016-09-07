package hr.ddcode.cafford.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.ddcode.cafford.business.component.auth.AuthenticationManager;
import hr.ddcode.cafford.data.dto.response.LoginDto;

@RestController
@RequestMapping("/login")
@Transactional
public class AuthenticationService {
	
	@Autowired
    private AuthenticationManager authManager;
	
	@RequestMapping(method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LoginDto loginUser(Authentication auth) {
		
		return authManager.getLoginData(auth);				
	}
}
