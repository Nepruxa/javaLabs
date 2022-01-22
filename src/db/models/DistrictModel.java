package db.models;

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

public class DistrictModel implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @OneToMany(mappedBy= "districtModel")
    private List<AddressModel> addresses = new ArrayList<AddressModel>();
	public static final long serialVersionUID = 1L;

	public DistrictModel() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public List<AddressModel> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<AddressModel> addresses) {
		this.addresses = addresses;
	}
   
}
