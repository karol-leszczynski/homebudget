package pl.karoll.spring.homebudget.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.karoll.spring.homebudget.model.Incomes;

import java.util.List;

public interface IncomeRepository extends JpaRepository <Incomes, Long> {

    List<Incomes> findByBudget_Id(Long id);

}
