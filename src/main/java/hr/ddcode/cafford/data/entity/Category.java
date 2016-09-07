package hr.ddcode.cafford.data.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "category", schema = "caffe_orders")
@Getter
@Setter
public class Category extends BaseEntity {

	private static final long serialVersionUID = 6316326577632244979L;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, name="image_url")
	private String imageUrl;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id",  nullable = false)
    private Category parent;
	
    @OneToMany(fetch = FetchType.LAZY, mappedBy="parent")
    private List<Category> children;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tenant_id", referencedColumnName = "id",  nullable = false)
	@JsonBackReference(value = "category_tenant_reference")
	private Tenant tenant;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	@JsonManagedReference(value = "item_category_reference")
    private List<Item> items;

}
