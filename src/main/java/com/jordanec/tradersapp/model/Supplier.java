package com.jordanec.tradersapp.model;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author jordan
 *
 */
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id",scope=Supplier.class)
@Table(name="Supplier")
public class Supplier extends BaseModel implements java.io.Serializable{
	@JsonIgnore
	private static final long serialVersionUID = 6414688052317334163L;
	private String name;
	private String company;
	private String address;
	private Integer phone;
	@ManyToMany(mappedBy="suppliers",fetch=FetchType.EAGER)
	//@JsonBackReference
	@JsonIgnore
	private Collection<Product> products;
	
	public Supplier() {	}
	
	public Supplier(String name, String company, String address, Integer phone) {
		super();
		this.name = name;
		this.company = company;
		this.address = address;
		this.phone = phone;
	}

	public Supplier(String name, String company, String address, Integer phone, Collection<Product> products) {
		super();
		this.name = name;
		this.company = company;
		this.address = address;
		this.phone = phone;
		this.products = products;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	@JoinTable(name = "Supplier_Products", catalog = "tradersappdb_v1", joinColumns = {
			@JoinColumn(name = "id", nullable = true, updatable = true) }, inverseJoinColumns = {
					@JoinColumn(name = "id", nullable = true, updatable = true) })
	public void setProducts(Collection<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return " {\"id\":\"" + id + "\",\"name\":\"" + name + "\"}";
	}
}
