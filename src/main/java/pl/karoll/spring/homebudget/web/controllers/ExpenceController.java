package pl.karoll.spring.homebudget.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.karoll.spring.homebudget.dto.ExpeneceDto;
import pl.karoll.spring.homebudget.service.ExpenceService;
import pl.karoll.spring.homebudget.service.TimeService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/expence")
public class ExpenceController {

    private TimeService timeService;
    private ExpenceService expenceService;

    public ExpenceController(TimeService timeService, ExpenceService expenceService) {
        this.timeService = timeService;
        this.expenceService = expenceService;
    }

    @GetMapping("/new")
    public String prepareExpenceForm(Model model) {
        model.addAttribute("newExpence", new ExpeneceDto());
        return "expence-form";
    }

    @GetMapping("/unexpected")
    public String prepareUnexpectedExpenceForm(Model model) {
        model.addAttribute("unexpectedExpence", new ExpeneceDto());
        return "unexpected-expence-form";
    }

    @GetMapping("/delete")
    private String deleteExpence(@RequestParam Long expenceId) {
        expenceService.deleteExpenceById(expenceId);
        return "redirect:/user";
    }

    @GetMapping("/type-switch")
    private String typeSwitch(@RequestParam Long expenceId){
        expenceService.switchTypeById(expenceId);
        return "redirect:/user";
    }

    @GetMapping("/payed-switch")
    private String payedSwitch(@RequestParam Long expenceId){
        expenceService.switchPayedById(expenceId);
        return "redirect:/user";
    }

    @PostMapping("/new")
    public String saveNewExpence(
            @ModelAttribute("newExpence")
            @Valid ExpeneceDto expeneceDto
            , BindingResult result
            , RedirectAttributes redirectAttributes
            , HttpSession session) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("expenceMessage"
                    , "Opis i Kwota nie mogą być puste");
            return "redirect:/user";
        }
        if (expeneceDto.getExpenceDescription().length() > 50) {
            redirectAttributes.addFlashAttribute("expenceMessage"
                    , "Maksymalna długość opisu to 50 znaków");
            return "redirect:/user";
        }
        if (expeneceDto.getExpenceAmmount() <= 0) {
            redirectAttributes.addFlashAttribute("expenceMessage"
                    , "Kwota musi być większa od zera");
            return "redirect:/user";
        }

        if (!expeneceDto.getPayDate().equals("")) {
            if (!timeService.isInCurrentBudgetPeriod(expeneceDto.parsedPayDate())) {
                redirectAttributes.addFlashAttribute("expenceMessage"
                        , "Data wydatku musi zawierać sie pomiędzy datą początkowa i datą końcową budżetu");
                return "redirect:/user";
            }
        }

        expenceService.addBudgetExpence(expeneceDto);

        return "redirect:/user";
    }

    @PostMapping("/unexpected")
    public String saveUnexpectedExpence(
            @ModelAttribute("unexpectedExpence")
            @Valid ExpeneceDto expeneceDto
            , BindingResult result
            , RedirectAttributes redirectAttributes
            , HttpSession session) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("unexpectedExpenceMessage"
                    , "Opis i Kwota nie mogą być puste");
            return "redirect:/user";
        }
        if (expeneceDto.getExpenceDescription().length() > 50) {
            redirectAttributes.addFlashAttribute("unexpectedExpenceMessage"
                    , "Maksymalna długość opisu to 50 znaków");
            return "redirect:/user";
        }
        if (expeneceDto.getExpenceAmmount() <= 0) {
            redirectAttributes.addFlashAttribute("unexpectedExpenceMessage"
                    , "Kwota musi być większa od zera");
            return "redirect:/user";
        }

        expenceService.addBudgetExpence(expeneceDto);

        return "redirect:/user";
    }

}
