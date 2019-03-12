package pl.karoll.spring.homebudget.service;

import org.springframework.stereotype.Service;
import pl.karoll.spring.homebudget.dto.IncomeDto;
import pl.karoll.spring.homebudget.model.Budget;
import pl.karoll.spring.homebudget.model.Incomes;
import pl.karoll.spring.homebudget.model.User;
import pl.karoll.spring.homebudget.repositories.BudgetRepository;
import pl.karoll.spring.homebudget.repositories.IncomeRepository;
import pl.karoll.spring.homebudget.repositories.UserRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class IncomeService {

private HttpSession session;
private IncomeRepository incomeRepository;
private BudgetRepository budgetRepository;
private UserRepository userRepository;

    public IncomeService(HttpSession session
            , IncomeRepository incomeRepository
            , BudgetRepository budgetRepository
            , UserRepository userRepository) {
        this.session = session;
        this.incomeRepository = incomeRepository;
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
    }

    public List<Incomes> getIncomesForCurrentBudget (){
        return incomeRepository
                .findByBudget_Id((Long) session.getAttribute("currentbudgetid"));
    }

    public List<Incomes> getIncomesForBudgetById (Long budgetId){
        return incomeRepository
                .findByBudget_Id(budgetId);
    }

    public void deleteIncomeById (Long incomeId){
        incomeRepository.deleteById(incomeId);
    }

    public List<IncomeDto> getIncomesDtoForCurrentBudget (){
        List<Incomes> incomes = getIncomesForCurrentBudget();
        List<IncomeDto> incomeDto = new ArrayList<>();
        for (Incomes income:incomes) {
            IncomeDto dto = new IncomeDto();
            dto.setId(income.getId());
            dto.setIncomeAmmount(income.getIncomeAmmount());
            dto.setType(income.getType());
            dto.setUserName(income.getUser().getUserName());
            incomeDto.add(dto);
        }return incomeDto;
    }

    public void addBudgedIncome (Double income){
        Budget budget = budgetRepository
                .getOne((Long) session.getAttribute("currentbudgetid"));
        User user = userRepository
                .getOne((Long) session.getAttribute("userid"));
        Incomes incomes = new Incomes();
        incomes.setType("b");
        incomes.setBudget(budget);
        incomes.setUser(user);
        incomes.setIncomeAmmount(income);
        incomeRepository.save(incomes);
}



}
