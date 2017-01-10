package com.jordanec.tradersapp.model;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "User", catalog="tradersappdb_v2")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id",scope=User.class)
public class User extends BaseModel implements Serializable  {
	@JsonIgnore
	private static final long serialVersionUID = 217670822776713839L;
    private String name;
    @Column(unique=true, nullable = false)
    private String email;
    private String password;
    
    public User() {}

    public User(User user) {
    	super();
    	this.name = user.name;
        this.email = user.email;
        this.password = user.password;
    }
    
    public User(String name, String email, String password) {
    	super();
		this.name = name;
		this.email = email;
		this.password = password;
	}
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public void setPassword(String password) {
        this.password = password;
    }

	public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}