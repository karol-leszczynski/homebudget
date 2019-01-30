package pl.karoll.spring.homebudget.web.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.karoll.spring.homebudget.dto.UserDto;
import pl.karoll.spring.homebudget.model.User;
import pl.karoll.spring.homebudget.repositories.UserRepository;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserDto userDto;

    public LoginController(UserRepository userRepository,
                           PasswordEncoder passwordEncoder, UserDto userDto) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDto = userDto;
    }

    @GetMapping
    public String prepareLoginForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "login";
    }

//    @PostMapping
//    public String loginUser(@ModelAttribute UserDto userDto,
//                            BindingResult result, HttpSession session) {
//        User userByEmail = userRepository.findByEmail(userDto.getEmail());
//        if (userByEmail == null) {
//            result.rejectValue("password", null, "Błędny email lub hasło");
//            return "login";
//        }
//        if (!passwordEncoder.matches(userDto.getPassword(),
//                userByEmail.getPassword())) {
//            result.rejectValue("password", null, "Błędny email lub hasło");
//            return "login";
//        }
//        session.setAttribute("userid", userByEmail.getId());
//        session.setAttribute("username", userByEmail.getUserName());
//        return "redirect:/user";
//    }

//    @GetMapping("/logout/old")
//    public String logout(HttpSession session) {
//        session.invalidate();
//        return "redirect:/";
//    }
}
