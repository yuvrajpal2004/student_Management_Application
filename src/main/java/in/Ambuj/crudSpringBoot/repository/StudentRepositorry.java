package in.Ambuj.crudSpringBoot.repository;

import in.Ambuj.crudSpringBoot.entitys.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
public interface StudentRepositorry  extends JpaRepository<Student, Long> {
    Optional<Student> findByIDAndDeletedIsFalse(Long id);
    List<Student> findByDeletedIsFalse();
    Boolean existsByEmail(String emailid);


}