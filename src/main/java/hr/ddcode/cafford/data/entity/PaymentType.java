package hr.ddcode.cafford.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payment_type", schema = "caffe_orders")
@Getter
@Setter
public class PaymentType extends BaseEntity {
	
	private static final long serialVersionUID = 5257559493153450097L;
	
	@Column(nullable = false, unique = true)
    private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tenant_id", referencedColumnName = "id", nullable = false)
	@JsonBackReference(value = "payment_tenant_reference")
	private Tenant tenant;
		
}
