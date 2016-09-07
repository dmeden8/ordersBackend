package hr.ddcode.cafford.data.type;

import lombok.Getter;

@Getter
public enum OrderStatus
{
	N("New"),
	I("Initial status"),
	P("In Progress"),
	F("Finished");
	
	private OrderStatus(String desc)
	{
		this.description = desc;
	}
		
	private String description;
	
}

