package pl.karoll.spring.homebudget.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.karoll.spring.homebudget.model.Budget;

import java.util.List;

public interface BudgetRepository extends JpaRepository <Budget, Long> {
    List<Budget> findAllByUsersIdOrderByStartDateDesc(Long id);
}
