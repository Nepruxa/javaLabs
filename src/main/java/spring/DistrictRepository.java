package spring;

import org.springframework.data.repository.CrudRepository;
import spring.model.District;

import java.util.List;

public interface DistrictRepository extends CrudRepository<District, Long> {
    District findById(long id);
}