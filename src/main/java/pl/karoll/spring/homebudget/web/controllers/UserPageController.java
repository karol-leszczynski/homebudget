package pl.karoll.spring.homebudget.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.karoll.spring.homebudget.dto.InvitationDto;
import pl.karoll.spring.homebudget.repositories.InvitationRepository;
import pl.karoll.spring.homebudget.service.BudgetService;
import pl.karoll.spring.homebudget.service.InvitationService;
import pl.karoll.spring.homebudget.service.UserService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/user")
public class UserPageController {

    private UserService userService;
    private BudgetService budgetService;
    private InvitationService invitationService;


    public UserPageController(UserService userService, BudgetService budgetService, InvitationService invitationService, InvitationRepository invitationRepository, InvitationService invitationService1) {
        this.userService = userService;
        this.budgetService = budgetService;
        this.invitationService = invitationService1;
    }

    @GetMapping
    public String userPage(Principal principal
            , Model model
            , HttpSession session){
        String currentUsermail = principal.getName();
        Long currentUserId = userService.currentUserIdByEmail(currentUsermail);
        userService.setCurrentUserDataToSession(currentUsermail);
        budgetService.setActualBudgdettoSession();
        DateTimeFormatter formatterLong = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatterShort = DateTimeFormatter.ofPattern("MM-yyyy");
        model.addAttribute("formatterShort", formatterShort);
        model.addAttribute("formatterLong", formatterLong);
        model.addAttribute("list", budgetService
                .currentUserBudgets(currentUserId));
        model.addAttribute("invitationDto", new InvitationDto());
        model.addAttribute("invitations", invitationService.invitationsList());
        return "user";
    }
}
