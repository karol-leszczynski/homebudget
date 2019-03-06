package pl.karoll.spring.homebudget.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.karoll.spring.homebudget.model.Budget;
import pl.karoll.spring.homebudget.service.BudgetService;
import pl.karoll.spring.homebudget.service.TimeService;

import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/budget")
public class BudgetSetterController {

    private BudgetService budgetService;
    private TimeService timeService;

    public BudgetSetterController(BudgetService budgetService, TimeService timeService) {
        this.budgetService = budgetService;
        this.timeService = timeService;
    }

    @GetMapping("/set")
    public String budgetSet(@RequestParam Long id) {
        budgetService.setCurrentBudgetIdToSession(id);
        return "redirect:/user";
    }

    @GetMapping("/delete")
    public String budgetDelete(@RequestParam Long budgetId) {
        budgetService.deleteBudget(budgetId);
        return "redirect:/user";
    }

    @GetMapping("/warning")
    public String budgetDeleteWarning(@RequestParam Long budgetId, Model model) {
        DateTimeFormatter formatterLong = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Budget budget = budgetService.budgetById(budgetId);
        model.addAttribute("formatterLong", formatterLong);
        model.addAttribute("budgetToDelete", budget);
        model.addAttribute("budgetEndDate"
                , timeService.endOfMonthPeriodDate(budget.getStartDate()));
        return "/delete-budget-warning";
    }

    @PostMapping("/set-savings")
    public String setSavingToBudget(@RequestParam Double savings
            , RedirectAttributes redirectAttributes) {
        if (savings <= 0) {
            redirectAttributes.addFlashAttribute("incomeMessage"
                    , "Oszczędności muszą być większe od zera");
            return "redirect:/user";
        }
        if(savings==null){
            redirectAttributes.addFlashAttribute("incomeMessage"
                    ,"Proszę podać wartość");
            return "redirect:/user";
        }
        budgetService.setSavingsToCurrentBudget(savings);
        return "redirect:/user";
    }

}
