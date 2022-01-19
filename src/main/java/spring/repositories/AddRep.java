package spring.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.models.Address;
import spring.models.District;

import java.util.List;

public interface AddRep extends CrudRepository<Address, Long> {
    List<Address> findAddressesByDistrict(District district);
}