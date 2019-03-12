package pl.karoll.spring.homebudget.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.karoll.spring.homebudget.model.Expences;

import java.util.List;

public interface ExpenceRepository extends JpaRepository <Expences, Long> {

    List<Expences>findByBudget_IdOrderByPayDateDesc(Long id);

}
