package spring.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.models.District;

public interface DistRep extends CrudRepository<District, Long> {
    District findById(long id);
}