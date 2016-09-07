package hr.ddcode.cafford.data.type;

import lombok.Getter;

@Getter
public enum UserStatus
{
	N("New"),
	A("Active"),
	S("Suspended");
	
	private UserStatus(String desc)
	{
		this.description = desc;
	}
		
	private String description;
	
}

