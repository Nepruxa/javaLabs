package spring;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.model.Parent;

public interface ParentRepository extends JpaRepository<Parent, Long> {
}