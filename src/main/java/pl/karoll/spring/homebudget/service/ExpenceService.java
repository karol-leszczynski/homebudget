package pl.karoll.spring.homebudget.service;

import org.springframework.stereotype.Service;
import pl.karoll.spring.homebudget.dto.ExpeneceDto;
import pl.karoll.spring.homebudget.model.Budget;
import pl.karoll.spring.homebudget.model.Expences;
import pl.karoll.spring.homebudget.repositories.BudgetRepository;
import pl.karoll.spring.homebudget.repositories.ExpenceRepository;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExpenceService {

    private HttpSession session;
    private BudgetRepository budgetRepository;
    private ExpenceRepository expenceRepository;

    public ExpenceService(HttpSession session, BudgetRepository budgetRepository, ExpenceRepository expenceRepository) {
        this.session = session;
        this.budgetRepository = budgetRepository;
        this.expenceRepository = expenceRepository;
    }

    public List<Expences> getExpencesForBudgetById(Long budgetId) {
        return expenceRepository.findByBudget_IdOrderByPayDateDesc(budgetId);
    }

    public void switchTypeById(Long expenceId) {
        Expences expence = expenceRepository.getOne(expenceId);
        if (expence.getType().equals("a")) {
            expence.setType("o");
            expenceRepository.save(expence);
        } else if (expence.getType().equals("o")) {
            expence.setType("a");
            expenceRepository.save(expence);
        }
    }

    public void switchPayedById(Long expenceId) {
        Expences expence = expenceRepository.getOne(expenceId);
        if (expence.isPayed()) {
            expence.setPayed(false);
            expenceRepository.save(expence);
        } else if (!expence.isPayed()) {
            expence.setPayed(true);
            expenceRepository.save(expence);
        }
    }

    public void addBudgetExpence(ExpeneceDto expeneceDto) {
        Budget budget = budgetRepository
                .getOne((Long) session.getAttribute("currentbudgetid"));
        Expences expence = new Expences();
        expence.setExpenceDescription(expeneceDto.getExpenceDescription());
        expence.setExpenceAmmount(expeneceDto.getExpenceAmmount());
        expence.setPayed(false);
        expence.setType(expeneceDto.getType());
        expence.setBudget(budget);
        if (!expeneceDto.getPayDate().equals("")) {
            expence.setPayDate(expeneceDto.parsedPayDate());
        }
        expenceRepository.save(expence);

    }

    public void deleteExpenceById(Long expenceId) {
        expenceRepository.deleteById(expenceId);
    }

    public List<Expences> expencesWithDate(List<Expences> expences) {
        return expences.stream()
                .filter(e -> e.getPayDate() != null)
                .collect(Collectors.toList());
    }

    public List<Expences> expiredExpences(List<Expences> expences) {
        List<Expences> dated = expencesWithDate(expences);
        return dated.stream()
                .filter(e -> e.getPayDate().isBefore(LocalDate.now()))
                .filter(e -> !e.isPayed())
                .collect(Collectors.toList());
    }

}
