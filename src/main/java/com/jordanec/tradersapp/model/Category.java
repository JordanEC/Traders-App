package com.jordanec.tradersapp.model;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author jordan
 *
 */
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id",scope=Category.class)
@Table(name="Category", catalog="tradersappdb_v2")
public class Category extends BaseModel implements java.io.Serializable{
	@JsonIgnore
	private static final long serialVersionUID = -7464018122626100689L;
	private String name;
	private String description;
	@OneToMany(mappedBy="category",targetEntity=Product.class,fetch=FetchType.EAGER)
	//@JsonBackReference
	@JsonIgnore
	private Collection<Product> products;
	
	public Category() {	}
	
	public Category(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
		
	public Category(String name, String description, Collection<Product> products) {
		super();
		this.name = name;
		this.description = description;
		this.products = products;
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
	public Collection<Product> getProducts() {
		return products;
	}
	public void setProducts(Collection<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return " {\"id\":\"" + id + "\",\"name\":\"" + name + "\"}";
	}
}
