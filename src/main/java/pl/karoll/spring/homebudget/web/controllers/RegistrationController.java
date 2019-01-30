package pl.karoll.spring.homebudget.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.karoll.spring.homebudget.dto.UserDto;
import pl.karoll.spring.homebudget.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String prepareRegistrationForm(Model model) {
        model.addAttribute("newUser", new UserDto());
        return "register";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("newUser") @Valid UserDto userDto,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        if (userService.ifExistByEmail(userDto.getEmail())) {
            result.rejectValue("email", null, "Email jest już w systemie");
            return "register";
        }
        if (userService.ifExistByName(userDto.getUserName())) {
            result.rejectValue("userName", null, "Ta nazwa jest już zajęta");
            return "register";
        }
        if (!userDto.getPassword().equals(userDto.getPasswordSecondCheck())){
            result.rejectValue("password", null, "Hasła muszą być jednakowe");
        }
        userService.saveNewUser(userDto);

        return "redirect:/login";
    }
}
