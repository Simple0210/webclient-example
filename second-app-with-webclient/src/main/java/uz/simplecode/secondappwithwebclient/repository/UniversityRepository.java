package uz.simplecode.secondappwithwebclient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.simplecode.secondappwithwebclient.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {

}
