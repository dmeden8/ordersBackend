package hr.ddcode.cafford.data.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tenant", schema = "caffe_orders")
@Getter
@Setter
public class Tenant extends BaseEntity {

	private static final long serialVersionUID = 1176278274642235084L;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tenant")
	@JsonManagedReference(value = "order_tenant_reference")
    private List<Order> orders;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tenant")
	@JsonManagedReference(value = "item_tenant_reference")
    private List<Item> items;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tenant")
	@JsonManagedReference(value = "category_tenant_reference")
    private List<Category> categories;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tenant")
	@JsonManagedReference(value = "payment_tenant_reference")
    private List<PaymentType> paymentTypes;
}
