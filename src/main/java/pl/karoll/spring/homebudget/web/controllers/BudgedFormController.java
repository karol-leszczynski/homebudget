package pl.karoll.spring.homebudget.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.karoll.spring.homebudget.dto.NewBudgetDto;
import pl.karoll.spring.homebudget.service.BudgetService;
import pl.karoll.spring.homebudget.service.TimeService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/budget")
public class BudgedFormController {

    private TimeService timeService;
    private BudgetService budgetService;

    public BudgedFormController(TimeService timeService
            , BudgetService budgetService) {
        this.timeService = timeService;
        this.budgetService = budgetService;
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
        if (timeService.odlerThanThisMonth(budgetDto.localDate())){
            result.rejectValue("stringDate", null
                    , "Nie można utworzyć nowego budżetu dla poprzenich miesięcy");
            return "/new-budget-form";
        }
        if (result.hasErrors()) {
            return "/new-budget-form";
        }
        budgetService.saveNewBudged(budgetDto);

        return "redirect:/user";
    }

}
