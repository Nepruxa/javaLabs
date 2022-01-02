package spring;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.model.Child;
import spring.model.Parent;

import java.util.List;

public interface ChildRepository extends JpaRepository<Child, Long> {
    List<Child> getChildrenByParentsContains(Parent p);
}