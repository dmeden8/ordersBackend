package hr.ddcode.cafford.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.ddcode.cafford.business.component.userAccount.UserAccountManager;
import hr.ddcode.cafford.data.dto.request.UserFilterDto;
import hr.ddcode.cafford.data.dto.request.UserRegisterDto;
import hr.ddcode.cafford.data.dto.request.UserStatusDto;
import hr.ddcode.cafford.data.dto.response.UserAccountDto;

@RestController
@RequestMapping("/user")
@Transactional
public class UserAccountService {
	
	@Autowired
    private UserAccountManager userAccountManager;
  
	
    @RequestMapping(value = "/register", 
    		method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Long registerUser(@RequestBody UserRegisterDto userRegisterDto) {
    	return userAccountManager.registerUser(userRegisterDto);
    }
      
    @RequestMapping(value = "/delete", 
    		method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(@RequestBody Long id) {
    	userAccountManager.delete(id);
    }
    
    @RequestMapping(value = "/list", 
    		method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserAccountDto> getFilteredUsers(@RequestBody UserFilterDto userFilterDto) {
    	return userAccountManager.getFilteredUsers(userFilterDto);    			
    }
       
    @RequestMapping(value = "/details", 
    		method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	public UserAccountDto getUserById(@RequestBody Long userId) {
    	return userAccountManager.getUserById(userId);  			
    }
        
    @RequestMapping(value = "/changestatus", 
    		method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String changeStatus(@RequestBody UserStatusDto userStatusDto) {
    	return userAccountManager.changeStatus(userStatusDto);  			
    }
    
    @RequestMapping(value = "/countstatus", 
    		method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long countByStatus(@RequestBody UserStatusDto userStatusDto) {
		return userAccountManager.countByStatus(userStatusDto.getStatus());						
    }
        
}
