package repository;

import model.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface childRepository extends JpaRepository<Child,Integer> {
    List<Child> findByPersonIsNull();
}
