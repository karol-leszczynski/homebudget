package pl.karoll.spring.homebudget.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.karoll.spring.homebudget.model.Expences;

public interface ExpenceRepository extends JpaRepository <Expences, Long> {

}
