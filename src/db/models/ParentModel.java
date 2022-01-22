package db.models;

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
public class ParentModel implements Serializable {

	   
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
	private AddressModel addressModel;
	@ManyToMany(cascade = {
		CascadeType.PERSIST,
	    CascadeType.MERGE
	})
	@JoinTable(name = "child_parent",
	    joinColumns = @JoinColumn(name = "parent_id"),
	    inverseJoinColumns = @JoinColumn(name = "child_id")
	)
	private List<ChildModel> children = new ArrayList<>();
	public static final long serialVersionUID = 1L;

	public ParentModel() {
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
	public AddressModel getAddress() {
		return this.addressModel;
	}

	public void setAddress(AddressModel addressModel) {
		this.addressModel = addressModel;
	}   
	public List<ChildModel> getChildren() {
		return this.children;
	}

	public void setChildren(List<ChildModel> children) {
		this.children = children;
	}

	public void addChild(ChildModel childModel) {
		this.children.add(childModel);
	}
   
}
