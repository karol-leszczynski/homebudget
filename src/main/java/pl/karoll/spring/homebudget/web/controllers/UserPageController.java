package pl.karoll.spring.homebudget.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.karoll.spring.homebudget.repositories.BudgetRepository;
import pl.karoll.spring.homebudget.service.BudgetService;
import pl.karoll.spring.homebudget.service.UserService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/user")
public class UserPageController {

    private UserService userService;
    private BudgetService budgetService;

    public UserPageController(UserService userService
            , BudgetService budgetService) {
        this.userService = userService;
        this.budgetService = budgetService;
    }

    @GetMapping
    public String userPage(Principal principal
            , Model model
            , HttpSession session){
        String currentUsermail = principal.getName();
        Long currentUserId = userService.currentUserIdByEmail(currentUsermail);
        userService.setCurrentUserDataToSession(currentUsermail);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
        model.addAttribute("formatter", formatter);
        model.addAttribute("list", budgetService
                .currentUserBudgets(currentUserId));
        return "user";
    }
}
