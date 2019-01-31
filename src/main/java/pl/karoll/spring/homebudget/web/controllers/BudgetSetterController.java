package pl.karoll.spring.homebudget.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.karoll.spring.homebudget.service.BudgetService;

@Controller
@RequestMapping("/budget")
public class BudgetSetterController {

    private BudgetService budgetService;

    public BudgetSetterController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping("/set")
    public String budgetSet (@RequestParam Long id){
        budgetService.setCurrentBuddgetDatesToSession(id);
        return "redirect:/user";
    }

    @GetMapping("/delete")
    public String budgetDelete (@RequestParam Long budgetId){
        budgetService.deleteBudget(budgetId);
        return "redirect:/user";
    }

}
