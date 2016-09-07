package hr.ddcode.cafford.data.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "item", schema = "caffe_orders")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Item extends BaseEntity {

	private static final long serialVersionUID = -8838000986916870470L;
	
	@Column(nullable = false, unique = true)
    private String name;
	
	@Column(nullable = false, unique = true)
    private String measure;
	
	@Column(nullable = false, unique = true, name = "external_id")
    private Long extrenalId;

    @Column(nullable = false, name = "mpc_price")
    private BigDecimal mpcPrice;
    
    @Column(nullable = false, name = "vpc_price")
    private BigDecimal vpcPrice;
    
    @Column(nullable = false)
    private Long discount;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tenant_id", referencedColumnName = "id",  nullable = false)
	@JsonBackReference(value = "item_tenant_reference")
	private Tenant tenant;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", referencedColumnName = "id",  nullable = false)
	@JsonBackReference(value = "item_category_reference")
	private Category category;
}
