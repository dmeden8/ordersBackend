package hr.ddcode.cafford.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "role", schema = "caffe_orders")
public class Role extends BaseEntity implements GrantedAuthority {

	private static final long serialVersionUID = 5400825879406886843L;
	
	@Getter(onMethod = @__({ @Override }))
    @Setter
    @Column(nullable = false, unique = true)
    private String authority;

}
