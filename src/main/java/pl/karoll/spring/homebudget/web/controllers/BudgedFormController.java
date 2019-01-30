package pl.karoll.spring.homebudget.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.karoll.spring.homebudget.dto.NewBudgetDto;
import pl.karoll.spring.homebudget.model.Budget;
import pl.karoll.spring.homebudget.repositories.BudgetRepository;
import pl.karoll.spring.homebudget.repositories.UserRepository;
import pl.karoll.spring.homebudget.service.TimeService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/budget")
public class BudgedFormController {

    private UserRepository userRepository;
    private BudgetRepository budgetRepository;
    private TimeService timeService;


    public BudgedFormController(UserRepository userRepository
            , BudgetRepository budgetRepository, TimeService timeService) {
        this.userRepository = userRepository;
        this.budgetRepository = budgetRepository;
        this.timeService = timeService;
    }

    @GetMapping("/new")
    public String prepareBudgetForm(Model model) {
        model.addAttribute("newBudget", new NewBudgetDto());
        return "new-budget-form";
    }

    @PostMapping("/new")
    public String saveNewBudget(
            @ModelAttribute("newBudget")
            @Valid NewBudgetDto budgetDto
            , BindingResult result
            , HttpSession session) {
        if (result.hasErrors()) {
            return "/new";
        }
        Budget budget = new Budget();
        budget.setStartDate(budgetDto.localDate());
        budget.setDaysInMonth(timeService.daysInMonth(budgetDto.localDate()));
        Long id = (Long) session.getAttribute("userid");
        budget.getUsers().add(userRepository.getOne(id));
        budgetRepository.save(budget);

        return "redirect:/user";
    }

}
