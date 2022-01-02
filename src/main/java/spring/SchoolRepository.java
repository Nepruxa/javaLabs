package spring;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.model.School;

public interface SchoolRepository extends JpaRepository<School, Long> {
    School getSchoolByNum(Integer num);
}