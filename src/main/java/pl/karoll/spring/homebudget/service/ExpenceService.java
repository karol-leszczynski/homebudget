package pl.karoll.spring.homebudget.service;

import org.springframework.stereotype.Service;
import pl.karoll.spring.homebudget.dto.ExpeneceDto;
import pl.karoll.spring.homebudget.model.Budget;
import pl.karoll.spring.homebudget.model.Expences;
import pl.karoll.spring.homebudget.repositories.BudgetRepository;
import pl.karoll.spring.homebudget.repositories.ExpenceRepository;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

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

    public void addBudgetExpence (ExpeneceDto expeneceDto){
        Budget budget = budgetRepository
                .getOne((Long) session.getAttribute("currentbudgetid"));
        Expences expence = new Expences();
        expence.setExpenceDescription(expeneceDto.getExpenceDescription());
        expence.setExpenceAmmount(expeneceDto.getExpenceAmmount());
        expence.setPayed(false);
        expence.setType(expeneceDto.getType());
        expence.setBudget(budget);
        if(!expeneceDto.getPayDate().equals("")){
            expence.setPayDate(expeneceDto.parsedPayDate());
        }
        expenceRepository.save(expence);

    }

}
