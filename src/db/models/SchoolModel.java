package db.models;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: School
 *
 */

@Entity
public class SchoolModel implements Serializable {

	   
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
	private AddressModel addressModel;
	private int num;
	public static final long serialVersionUID = 1L;

	public SchoolModel() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public AddressModel getAddress() {
		return this.addressModel;
	}

	public void setAddress(AddressModel addressModel) {
		this.addressModel = addressModel;
	}   
	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}
   
}
