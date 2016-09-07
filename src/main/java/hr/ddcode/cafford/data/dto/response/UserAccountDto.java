package hr.ddcode.cafford.data.dto.response;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import hr.ddcode.cafford.data.type.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties({"dtf"})
public class UserAccountDto {
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
	
	private String name;
	
	private String username;
	
	private String email;
	
    private String oib;
    
    private String address;
    
    private String ownerName;
    
    private String telNumber;
    
    private Long id;
    
    private String registerTime;
    
    private String activationTime;
    
    private UserStatus status;
    
    
    public void setRegisterTime(ZonedDateTime creationTime) {
		this.registerTime = creationTime.format(dtf);
	}
    
    public void setActivationTime(ZonedDateTime creationTime) {
		this.activationTime = creationTime.format(dtf);
	}

}
