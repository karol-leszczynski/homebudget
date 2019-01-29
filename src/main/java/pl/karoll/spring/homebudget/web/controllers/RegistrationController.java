package pl.karoll.spring.homebudget.web.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.karoll.spring.homebudget.model.User;
import pl.karoll.spring.homebudget.repositories.UserRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String prepareRegistrationForm(Model model){
        model.addAttribute("newUser", new User());
        return "register";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("newUser") @Valid User user,
                               BindingResult result){
        if (result.hasErrors()){
            return "register";
        }
        User userByEmail = userRepository.findByEmail(user.getEmail());
        if (userByEmail != null){
            result.rejectValue("email", null, "Email jest już w systemie");
            return "register";
        }
        User userByName = userRepository.findByUserName(user.getUserName());
        if (userByName != null){
            result.rejectValue("userName", null, "Ta nazwa jest już zajęta");
            return "register";
        }
        user.setEnabled(true);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        return "redirect:/login";
    }
}
