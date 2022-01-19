package spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.models.Child;
import spring.models.Parent;

import java.util.List;

public interface ChildRep extends JpaRepository<Child, Long> {
    List<Child> getChildrenByParentsContains(Parent p);
}