package spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.models.School;

public interface SchlRep extends JpaRepository<School, Long> {
    School getSchoolByNum(Integer num);
}