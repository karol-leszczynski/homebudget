package pl.karoll.spring.homebudget.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.karoll.spring.homebudget.service.IncomeService;

@Controller
@RequestMapping("/income")
public class IncomeController {

    private IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping("/add")
    private String addIncome (@RequestParam Double income
    , RedirectAttributes redirectAttributes){
        if(income<=0){
            redirectAttributes.addFlashAttribute("savingsMessage"
            ,"Wpływy muszą być większe od zera");
            return "redirect:/user";
        }
        if(income==null){
            redirectAttributes.addFlashAttribute("savingsMessage"
                    ,"Proszę podać wartość");
            return "redirect:/user";
        }
        incomeService.addBudgedIncome(income);
        return "redirect:/user";
    }

    @GetMapping("/delete")
    private String deleteIncome (@RequestParam Long incomeId){
        incomeService.deleteIncomeById(incomeId);
        return "redirect:/user";
    }

}