package pl.karoll.spring.homebudget.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.karoll.spring.homebudget.dto.ExpeneceDto;
import pl.karoll.spring.homebudget.dto.InvitationDto;
import pl.karoll.spring.homebudget.repositories.InvitationRepository;
import pl.karoll.spring.homebudget.service.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserPageController {

    private UserService userService;
    private BudgetService budgetService;
    private InvitationService invitationService;
    private TimeService timeService;

    public UserPageController(UserService userService, BudgetService budgetService, InvitationService invitationService, TimeService timeService) {
        this.userService = userService;
        this.budgetService = budgetService;
        this.invitationService = invitationService;
        this.timeService = timeService;
    }

    @GetMapping
    public String userPage(Principal principal
            , Model model, HttpSession session) {
        String currentUsermail = principal.getName();
        userService.gatherCurrentUserDataByEmailAndSetUserDataToSession(currentUsermail);
        model.addAttribute("list", budgetService
                .currentUserBudgets((Long) session.getAttribute("userid")));
        model.addAttribute("invitations", invitationService.invitationsList());
        model.addAttribute("formatterShort", timeService.formatterShort);
        model.addAttribute("formatterLong", timeService.formatterLong);
        model.addAttribute("formatterShortDays", timeService.formatterShortDays);
        model.addAttribute("currentDate", timeService.currentDate());
        model.addAttribute("currentTime", timeService.currentDateTime());
        model.addAttribute("currentBudgetDto", budgetService
                .getCurrentBudgetDto((Long) session.getAttribute("currentbudgetid")));
        return "user";
    }

}
