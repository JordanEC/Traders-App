package com.jordanec.tradersapp.model;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id",scope=Product.class)
public class Product implements java.io.Serializable{
	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String name;
	String description;
	Double unitPrice;
	Integer unitsInStock;
	@ManyToOne
	//@JsonManagedReference
	Category category;
	@ManyToMany(fetch=FetchType.EAGER)
	//@JsonManagedReference
	Collection<Supplier> suppliers;
	
	public Product() { }
	
	public Product(Long id, String name, String description, Double unitPrice, Integer unitsInStock) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.unitPrice = unitPrice;
		this.unitsInStock = unitsInStock;
	}

	public Product(Long id, String name, String description, Double unitPrice, Integer unitsInStock,
			Category category) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.unitPrice = unitPrice;
		this.unitsInStock = unitsInStock;
		this.category = category;
	}

	public Product(Long id, String name, String description, Double unitPrice, Integer unitsInStock, Category category,
			Collection<Supplier> suppliers) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.unitPrice = unitPrice;
		this.unitsInStock = unitsInStock;
		this.category = category;
		this.suppliers = suppliers;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
