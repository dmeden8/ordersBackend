package hr.ddcode.cafford.data.entity;

import java.time.ZonedDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import hr.ddcode.cafford.data.type.UserStatus;
import hr.ddcode.cafford.utility.converter.ZonedDateTimeConverter;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_account", schema = "caffe_orders")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "password" , "roles", "tenants"})
public class UserAccount extends BaseEntity {

	private static final long serialVersionUID = 4977643314089268247L;
	
	@JsonSerialize(converter = ZonedDateTimeConverter.Serializer.class)
	@JsonDeserialize(converter = ZonedDateTimeConverter.Serializer.class)
	@Column(nullable = false, name="register_time")
	private ZonedDateTime registerTime;
	
	@JsonSerialize(converter = ZonedDateTimeConverter.Serializer.class)
	@JsonDeserialize(converter = ZonedDateTimeConverter.Serializer.class)
	@Column(nullable = false, name="activation_time")
	private ZonedDateTime activationTime;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
    private UserStatus status;

	@Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String oib;
    
    @Column(nullable = false, unique = true)
    private String address;
    
    @Column(nullable = false, unique = true, name="owner_name")
    private String ownerName;
    
    @Column(nullable = false, unique = true, name="tel_number")
    private String telNumber;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_tenant", schema = "caffe_orders",
            joinColumns = @JoinColumn(name = "user_account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tenant_id", referencedColumnName = "id"))
    private Set<Tenant> tenants;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", schema = "caffe_orders",
            joinColumns = @JoinColumn(name = "user_account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
    
    /*
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccount")
    @JsonManagedReference(value = "order_userAccount_reference")	
    private Set<Order> orders;
    */

    public UserAccount() {}
    
    public UserAccount(String username, String password) {
    	this.username = username;
        this.password = password;
    }
    
    public UserAccount(final UserAccount account) {
        username = account.getUsername(); 
        password = account.getPassword();
        email = account.getEmail();
        roles = account.getRoles();
        tenants = account.getTenants();
        name = account.getName();
        id = account.getId();
        status = account.getStatus();
    }
    

}
