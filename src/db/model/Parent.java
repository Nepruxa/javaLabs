package db.model;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Parent
 *
 */
@Entity
public class Parent implements Serializable {

	   
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	@ManyToMany(cascade = {
		CascadeType.PERSIST,
	    CascadeType.MERGE
	})
	@JoinTable(name = "child_parent",
	    joinColumns = @JoinColumn(name = "parent_id"),
	    inverseJoinColumns = @JoinColumn(name = "child_id")
	)
	private List<Child> children = new ArrayList<>();
	public static final long serialVersionUID = 1L;

	public Parent() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}   
	public List<Child> getChildren() {
		return this.children;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}

	public void addChild(Child child) {
		this.children.add(child);
	}
   
}
