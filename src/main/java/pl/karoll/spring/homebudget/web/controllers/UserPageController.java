package pl.karoll.spring.homebudget.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.karoll.spring.homebudget.dto.ExistingBudgetDto;
import pl.karoll.spring.homebudget.service.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserPageController {

    private UserService userService;
    private BudgetService budgetService;
    private InvitationService invitationService;
    private TimeService timeService;
    private CalendarService calendarService;

    public UserPageController(UserService userService, BudgetService budgetService, InvitationService invitationService, TimeService timeService, CalendarService calendarService) {
        this.userService = userService;
        this.budgetService = budgetService;
        this.invitationService = invitationService;
        this.timeService = timeService;
        this.calendarService = calendarService;
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
        ExistingBudgetDto currentBudgetDto = budgetService.getCurrentBudgetDto();
        model.addAttribute("currentBudgetDto", currentBudgetDto);
        model.addAttribute("calendar"
                , calendarService.getCalendarForBudget(currentBudgetDto));
        return "user";
    }

}
