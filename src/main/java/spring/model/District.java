package spring.model;

import java.io.Serializable;
import java.lang.Long;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: District
 *
 */
@Entity

public class District implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @OneToMany(mappedBy="district")
    private List<Address> addresses = new ArrayList<Address>();
	public static final long serialVersionUID = 1L;

	public District() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
   
}
