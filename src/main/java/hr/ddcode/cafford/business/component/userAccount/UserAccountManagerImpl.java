package hr.ddcode.cafford.business.component.userAccount;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import hr.ddcode.cafford.data.dto.request.UserFilterDto;
import hr.ddcode.cafford.data.dto.request.UserRegisterDto;
import hr.ddcode.cafford.data.dto.request.UserStatusDto;
import hr.ddcode.cafford.data.dto.response.UserAccountDto;
import hr.ddcode.cafford.data.entity.Role;
import hr.ddcode.cafford.data.entity.Tenant;
import hr.ddcode.cafford.data.entity.UserAccount;
import hr.ddcode.cafford.data.repository.role.RoleRepository;
import hr.ddcode.cafford.data.repository.tenant.TenantRepository;
import hr.ddcode.cafford.data.repository.userAccount.UserAccountRepository;
import hr.ddcode.cafford.data.type.UserStatus;

@Component
public class UserAccountManagerImpl implements UserAccountManager {
		
	@Autowired
    private UserAccountRepository userAccRepo;
	
	@Autowired
    private TenantRepository tenantRepo;
	
	@Autowired
    private RoleRepository roleRepo;
	
	@Autowired
    private BCryptPasswordEncoder bCryptEncoder;
	
	
    public Long registerUser(UserRegisterDto userRegisterDto) {
    	
    	Role role = roleRepo.findOne(2L);
    	Tenant tenant = tenantRepo.findOne(userRegisterDto.getTenantId());
    	
    	Set<Role> roles = new HashSet<Role>();
    	roles.add(role);
    	
    	Set<Tenant> tenants = new HashSet<Tenant>();
    	tenants.add(tenant);
    	
    	UserAccount userAccount = new UserAccount();   	
    	
    	userAccount.setPassword(bCryptEncoder.encode(userRegisterDto.getPassword()));
    	userAccount.setUsername(userRegisterDto.getUsername());
    	userAccount.setAddress(userRegisterDto.getAddress());
    	userAccount.setEmail(userRegisterDto.getEmail());
    	userAccount.setTelNumber(userRegisterDto.getTelNumber());
    	userAccount.setName(userRegisterDto.getName());
    	userAccount.setOib(userRegisterDto.getOib());
    	userAccount.setOwnerName(userRegisterDto.getOwnerName());
    	userAccount.setRoles(roles);
    	userAccount.setTenants(tenants);
    	userAccount.setRegisterTime(ZonedDateTime.now(ZoneId.of("Europe/Zagreb")));
    	userAccount.setStatus(UserStatus.N);
    	
    	UserAccount storedAcc = userAccRepo.save(userAccount);
    	
        return storedAcc == null ? null : storedAcc.getId();
    }
	
    public void delete(Long id) {
    	userAccRepo.delete(id);
    }
	
    public List<UserAccountDto> getFilteredUsers(UserFilterDto userFilterDto) {
    	Tenant tenant = tenantRepo.findOne(userFilterDto.getTenantId());
    	Set<Tenant> tenants = new HashSet<Tenant>();
    	tenants.add(tenant);
    	
    	List<UserAccount> userAccounts = new ArrayList<UserAccount>();
    	if (userFilterDto.getStatus() != null) {
    		userAccounts = userAccRepo.findByTenantAndStatus(tenants,userFilterDto.getStatus());	
    	}
    	else {
    		userAccounts = userAccRepo.findByTenant(tenants);	
    	}
    	
		List<UserAccountDto> userAccountsDto = new ArrayList<UserAccountDto>();
		
		for (UserAccount userAccount : userAccounts) {
			UserAccountDto userAccountDto = new UserAccountDto();			
			userAccountDto.setEmail(userAccount.getEmail());
			userAccountDto.setName(userAccount.getName());
			userAccountDto.setStatus(userAccount.getStatus());
			userAccountDto.setAddress(userAccount.getAddress());
			userAccountDto.setId(userAccount.getId());
			userAccountsDto.add(userAccountDto);
		}
		
		return userAccountsDto;	
    }
	
    public UserAccountDto getUserById(Long userId) {
    	UserAccount userAccount = userAccRepo.findOne(userId);	
		
    	UserAccountDto userAccountDto = new UserAccountDto();			
    	userAccountDto.setEmail(userAccount.getEmail());
		userAccountDto.setName(userAccount.getName());
		userAccountDto.setUsername(userAccount.getUsername());
		userAccountDto.setId(userAccount.getId());
		userAccountDto.setRegisterTime(userAccount.getRegisterTime());
		userAccountDto.setOib(userAccount.getOib());
		userAccountDto.setOwnerName(userAccount.getOwnerName());
		userAccountDto.setStatus(userAccount.getStatus());
		userAccountDto.setAddress(userAccount.getAddress());
		userAccountDto.setTelNumber(userAccount.getTelNumber());
		if (userAccount.getActivationTime() != null) {
			userAccountDto.setActivationTime(userAccount.getActivationTime());
		}
		    			
		return userAccountDto;	
    }
	
    public String changeStatus(UserStatusDto userStatusDto) {   	
		UserAccount userAccount = userAccRepo.findOne(userStatusDto.getUserId());	
		userAccount.setStatus(userStatusDto.getStatus());
		if (userStatusDto.getStatus() == UserStatus.A) { 
			userAccount.setActivationTime(ZonedDateTime.now(ZoneId.of("Europe/Zagreb")));
		}
		
		userAccRepo.saveAndFlush(userAccount);
		
		return userAccount.getStatus().name();	
    }
    
    public Long countByStatus(UserStatus userStatus) {
    	return userAccRepo.countByStatus(userStatus);
    }
	
}
