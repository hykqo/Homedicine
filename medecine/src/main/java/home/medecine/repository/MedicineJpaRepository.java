package home.medecine.repository;

import home.medecine.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineJpaRepository extends JpaRepository<Medicine, Long> {

}
