package pl.karoll.spring.homebudget.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.karoll.spring.homebudget.model.User;
import pl.karoll.spring.homebudget.repositories.BudgetRepository;
import pl.karoll.spring.homebudget.repositories.UserRepository;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/user")
public class UserPageController {

    private UserRepository userRepository;
    private BudgetRepository budgetRepository;

    public UserPageController(UserRepository userRepository, BudgetRepository budgetRepository) {
        this.userRepository = userRepository;
        this.budgetRepository = budgetRepository;
    }


    @GetMapping
    public String userPage(Principal principal
            , Model model
            , HttpSession session){
        String currentUsermail = principal.getName();
        User currentUser = userRepository.findByEmail(currentUsermail);
        String currentUserName = currentUser.getUserName();
        Long currentUserId = currentUser.getId();
        session.setAttribute("name", currentUserName);
        session.setAttribute("userid", currentUserId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
        model.addAttribute("list", budgetRepository
                .findAllByUsersIdOrderByStartDateDesc(currentUserId));
        model.addAttribute("formatter", formatter);
        return "user";
    }

    @GetMapping("/test")
    @ResponseBody
    public String userTest (){
        return String.valueOf(budgetRepository.findAllByUsersIdOrderByStartDateDesc(4L));
    }



}
