package spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.models.Parent;

public interface ParRep extends JpaRepository<Parent, Long> {
}