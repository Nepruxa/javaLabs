package db.models;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Child
 *
 */

@Entity
public class ChildModel implements Serializable {

	   
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(name = "child_parent",
			joinColumns = @JoinColumn(name = "child_id"),
			inverseJoinColumns = @JoinColumn(name = "parent_id")
	)
	private List<ParentModel> parents = new ArrayList<ParentModel>();;
	private int age;
	@ManyToOne
	private SchoolModel schoolModel;
	public static final long serialVersionUID = 1L;

	public ChildModel() {
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
	public List<ParentModel> getParents() {
		return this.parents;
	}

	public void setParents(List<ParentModel> parents) {
		this.parents = parents;
	}   
	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}   
	public SchoolModel getSchool() {
		return this.schoolModel;
	}

	public void setSchool(SchoolModel schoolModel) {
		this.schoolModel = schoolModel;
	}

}
