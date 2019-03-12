package pl.karoll.spring.homebudget.service;

import org.springframework.stereotype.Service;
import pl.karoll.spring.homebudget.dto.ExistingBudgetDto;
import pl.karoll.spring.homebudget.dto.NewBudgetDto;
import pl.karoll.spring.homebudget.model.Budget;
import pl.karoll.spring.homebudget.model.Expences;
import pl.karoll.spring.homebudget.model.Incomes;
import pl.karoll.spring.homebudget.repositories.BudgetRepository;
import pl.karoll.spring.homebudget.repositories.UserRepository;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class BudgetService {

    private UserRepository userRepository;
    private BudgetRepository budgetRepository;
    private TimeService timeService;
    private HttpSession httpSession;
    private IncomeService incomeService;
    private ExpenceService expenceService;

    public BudgetService(UserRepository userRepository
            , BudgetRepository budgetRepository
            , TimeService timeService, HttpSession httpSession
            , IncomeService incomeService, ExpenceService expenceService) {
        this.userRepository = userRepository;
        this.budgetRepository = budgetRepository;
        this.timeService = timeService;
        this.httpSession = httpSession;
        this.incomeService = incomeService;
        this.expenceService = expenceService;
    }

    public Budget budgetById(Long budgetId) {
        return budgetRepository.getOne(budgetId);
    }

    public List<Budget> currentUserBudgets(Long currentUserId) {
        List<Budget> list = budgetRepository.findAllByUsersIdOrderByStartDateDesc(currentUserId);
        setActualBudgdetToSession(list);
        return list;
    }

    public void setSavingsToCurrentBudget(Double savings) {
        Budget updatedBudget = budgetRepository.getOne((Long) httpSession
                .getAttribute("currentbudgetid"));
        updatedBudget.setSavings(savings);
        budgetRepository.save(updatedBudget);
    }

    public void deleteBudget(Long budgetId) {
//        manual delete incomes
        List<Incomes> incomes = incomeService.getIncomesForBudgetById(budgetId);
        for (Incomes income : incomes) {
            incomeService.deleteIncomeById(income.getId());
        }
//        manual delete expences
        List<Expences> expences = expenceService.getExpencesForBudgetById(budgetId);
        for (Expences expence : expences) {
            expenceService.deleteExpenceById(expence.getId());
        }
        budgetRepository.deleteById(budgetId);
        httpSession.removeAttribute("currentbudgetid");
    }

    public void saveNewBudged(NewBudgetDto newBudgetDto) {
        Budget budget = new Budget();
        budget.setStartDate(newBudgetDto.localDate());
        budget.setDaysInMonth(timeService.daysInMonth(newBudgetDto.localDate()));
        Long id = (Long) httpSession.getAttribute("userid");
        budget.getUsers().add(userRepository.getOne(id));
        budgetRepository.save(budget);
    }

    public void setActualBudgdetToSession(List<Budget> list) {
        if (httpSession.getAttribute("currentbudgetid") == null) {
            LocalDate currentDate = LocalDate.now();
            for (Budget budget : list) {
                if (currentDate.getMonth().equals(budget.getStartDate().getMonth())) {
                    httpSession.setAttribute("currentbudgetid",
                            budget.getId());
                }
            }
        }
    }

    public void setCurrentBudgetIdToSession(Long id) {
        httpSession.setAttribute("currentbudgetid", id);
    }

    public ExistingBudgetDto getCurrentBudgetDto() {
        if (httpSession.getAttribute("currentbudgetid") != null) {
            Long currentBudgetId = (Long) httpSession.getAttribute("currentbudgetid");
            Budget currentBudget = budgetRepository.getOne(currentBudgetId);
            ExistingBudgetDto currentBudgetDto = new ExistingBudgetDto();
            currentBudgetDto.setId(currentBudget.getId());
            currentBudgetDto.setSavings(currentBudget.getSavings());
            currentBudgetDto.setStartDate(currentBudget.getStartDate());
            currentBudgetDto.setEndDate(timeService
                    .endOfMonthPeriodDate(currentBudget.getStartDate()));
            currentBudgetDto.setDaysInMoth(currentBudget.getDaysInMonth());
            currentBudgetDto.setUsers(currentBudget.getUsers());
            currentBudgetDto.setIncomes(incomeService
                    .getIncomesForBudgetById(currentBudgetId));
            currentBudgetDto.setExpences(expenceService
                    .getExpencesForBudgetById(currentBudgetId));
            httpSession.setAttribute("currentBudgetStartDate"
                    , currentBudgetDto.getStartDate());
            httpSession.setAttribute("currentBudgetEndDate"
                    , currentBudgetDto.getEndDate());
            return currentBudgetDto;
        }
        return null;
    }

}
