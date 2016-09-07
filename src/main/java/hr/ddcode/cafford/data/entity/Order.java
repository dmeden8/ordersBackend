package hr.ddcode.cafford.data.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import hr.ddcode.cafford.data.type.OrderStatus;
import hr.ddcode.cafford.utility.converter.ZonedDateTimeConverter;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders", schema = "caffe_orders")
@Getter
@Setter
public class Order extends BaseEntity {
	
	private static final long serialVersionUID = 7169930941801893097L;
	
	@JsonSerialize(converter = ZonedDateTimeConverter.Serializer.class)
	@JsonDeserialize(converter = ZonedDateTimeConverter.Serializer.class)
	@Column(nullable = false, name="creation_time")
	private ZonedDateTime creationTime;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
    private OrderStatus status;
	
	@Column(nullable = false, name="total_price")
	private BigDecimal totalPrice;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_account_id", referencedColumnName = "id", nullable = false)
	private UserAccount userAccount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_type_id", referencedColumnName = "id", nullable = false)
	private PaymentType paymentType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tenant_id", referencedColumnName = "id", nullable = false)
	@JsonBackReference(value = "order_tenant_reference")
	private Tenant tenant;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
	@JsonManagedReference(value = "item_order_reference")
    private List<OrderItem> orderItems;

}
