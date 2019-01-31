package pl.karoll.spring.homebudget.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.karoll.spring.homebudget.service.InvitationService;
import pl.karoll.spring.homebudget.service.UserService;

import java.time.LocalDate;

@Controller
@RequestMapping("/invitation")
public class InvitationSendController {

    private UserService userService;
    private InvitationService invitationService;

    public InvitationSendController(UserService userService, InvitationService invitationService) {
        this.userService = userService;
        this.invitationService = invitationService;
    }


    @PostMapping("/send")
    private String invitationSend(@RequestParam String reciverEmail
            , RedirectAttributes redirectAttributes) {
        if (!userService.ifExistByEmail(reciverEmail)) {
            redirectAttributes.addFlashAttribute(
                    "inviteMessage",
                    "Nie ma takiego użytkownika w systemie");
            return "redirect:/user";
        }
        invitationService.saveInvitationWithReciverEmail(reciverEmail);
        redirectAttributes.addFlashAttribute(
                "inviteMessage",
                "Zaproszenie wysłane");
        return "redirect:/user";
    }

    @GetMapping("/delete")
    private String invitationDelete(@RequestParam Long invitationId) {
        invitationService.deleteInvitation(invitationId);
        return "redirect:/user";
    }

    @GetMapping("/accept")
    private String invitationAccept(@RequestParam Long userId
            , Long budgetId
            , Long invitationId) {
        invitationService.addUserToBudget(userId, budgetId);
        invitationService.deleteInvitation(invitationId);
        return "redirect:/user";
    }

}
