package hr.ddcode.cafford.business.component.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import hr.ddcode.cafford.business.UserPrincipal;
import hr.ddcode.cafford.data.dto.response.LoginDto;
import hr.ddcode.cafford.data.entity.Tenant;

@Component
public class AuthenticationManagerImpl implements AuthenticationManager {
	
	public LoginDto getLoginData(Authentication auth) {
		
		UserPrincipal principal = (UserPrincipal)auth.getPrincipal();
		
		LoginDto loginDto = new LoginDto();
		loginDto.setStatus(principal.getStatus());
		loginDto.setName(principal.getName());
		loginDto.setId(principal.getId());
		
		List<Long> tenantIds = new ArrayList<Long>();
		for (Tenant tenant : principal.getTenants()) {
			tenantIds.add(tenant.getId());
		}
		
		loginDto.setTenantIds(tenantIds);
		
		return loginDto;
		
	}

}
