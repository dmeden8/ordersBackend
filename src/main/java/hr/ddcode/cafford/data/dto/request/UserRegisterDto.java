package hr.ddcode.cafford.data.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDto {
			
	private String name;
	
	private String username;
	
	private String password;
	
	private Long tenantId;
	
	private String email;
	
    private String oib;
    
    private String address;
    
    private String ownerName;
    
    private String telNumber;
    
}
