package db.models;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Address
 *
 */
@Entity

public class AddressModel implements Serializable {

	   
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String address;
    @ManyToOne
    @JoinColumn(name="district_id", nullable=false)
    private DistrictModel districtModel;
	public static final long serialVersionUID = 1L;

	public AddressModel() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public DistrictModel getDistrict() {
		return districtModel;
	}
	public void setDistrict(DistrictModel districtModel) {
		this.districtModel = districtModel;
	}
   
}
