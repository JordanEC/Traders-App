package com.jordanec.tradersapp.model;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.jordanec.tradersapp.model.Supplier;

/**
 * @author jordan
 *
 */
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id",scope=Product.class)
@Table(name="Product", catalog="tradersappdb_v2")
public class Product extends BaseModel implements java.io.Serializable{
	@JsonIgnore
	private static final long serialVersionUID = 2723572977273902717L;
	private String name;
	private String description;
	private Double unitPrice;
	private Integer unitsInStock;
	@ManyToOne
	//@JsonManagedReference
	private Category category;
	@ManyToMany(fetch=FetchType.EAGER)
	//@JsonManagedReference
	private Collection<Supplier> suppliers;
	
	public Product() { }
	
	public Product(String name, String description, Double unitPrice, Integer unitsInStock) {
		super();
		this.name = name;
		this.description = description;
		this.unitPrice = unitPrice;
		this.unitsInStock = unitsInStock;
	}

	public Product(String name, String description, Double unitPrice, Integer unitsInStock,
			Category category) {
		super();
		this.name = name;
		this.description = description;
		this.unitPrice = unitPrice;
		this.unitsInStock = unitsInStock;
		this.category = category;
	}

	public Product(String name, String description, Double unitPrice, Integer unitsInStock, Category category,
			Collection<Supplier> suppliers) {
		super();
		this.name = name;
		this.description = description;
		this.unitPrice = unitPrice;
		this.unitsInStock = unitsInStock;
		this.category = category;
		this.suppliers = suppliers;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Collection<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Collection<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	public Integer getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(Integer unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	
	@Override
	public String toString() {
		return " {\"id\":\"" + id + "\",\"name\":\"" + name + "\",\"description\":\"" + description
				+ "\",\"unitPrice\":\"" + unitPrice + "\",\"unitsInStock\":\"" + unitsInStock + "\",\"category\":\""
				+ category + "\",\"suppliers\":\"" + suppliers + "\"}";
	}
	
	
	
}
