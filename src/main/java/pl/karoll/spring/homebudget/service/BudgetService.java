package pl.karoll.spring.homebudget.service;

import org.springframework.stereotype.Service;
import pl.karoll.spring.homebudget.dto.NewBudgetDto;
import pl.karoll.spring.homebudget.model.Budget;
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

    public BudgetService(UserRepository userRepository
            , BudgetRepository budgetRepository
            , TimeService timeService, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.budgetRepository = budgetRepository;
        this.timeService = timeService;
        this.httpSession = httpSession;
    }

    public Long currentUserIdFromSeesion() {
        return (Long) httpSession.getAttribute("userid");
    }

    public List<Budget> currentUserBudgets(Long currentUserId) {
        return budgetRepository.findAllByUsersIdOrderByStartDateDesc(currentUserId);
    }

    public void saveNewBudged(NewBudgetDto newBudgetDto) {
        Budget budget = new Budget();
        budget.setStartDate(newBudgetDto.localDate());
        budget.setDaysInMonth(timeService.daysInMonth(newBudgetDto.localDate()));
        Long id = (Long) httpSession.getAttribute("userid");
        budget.getUsers().add(userRepository.getOne(id));
        budgetRepository.save(budget);
    }

    public void setActualBudgdettoSession() {
        if (httpSession.getAttribute("currentbudgetid") == null) {
            List<Budget> list = currentUserBudgets(currentUserIdFromSeesion());
            LocalDate currentDate = LocalDate.now();
            for (Budget budget : list) {
                if (currentDate.getMonth().equals(budget.getStartDate().getMonth())) {
                    httpSession.setAttribute("currentbudgetid",
                            budget.getId());
                }
            }
        }
    }

}
