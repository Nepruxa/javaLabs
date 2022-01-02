package spring;

import org.springframework.data.repository.CrudRepository;
import spring.model.Address;
import spring.model.District;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Long> {
    List<Address> findAddressesByDistrict(District district);
}