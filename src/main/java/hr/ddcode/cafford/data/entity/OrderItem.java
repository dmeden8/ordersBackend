package hr.ddcode.cafford.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_item", schema = "caffe_orders")
@Getter
@Setter
public class OrderItem extends BaseEntity {

	private static final long serialVersionUID = 6309372553043409134L;
	
	@Column(nullable = false)
    private Integer amount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
	@JsonBackReference(value = "item_order_reference")
	private Order order;
		
	@OneToOne(fetch = FetchType.LAZY)
	private Item item;
	
	

}
