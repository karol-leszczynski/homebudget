package pl.karoll.spring.homebudget.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
@RequestMapping
public class JspTestContoller {

    @GetMapping("/testjsp")
    public String testjsp() {
        return "test";
    }

    @PostMapping("/testjsp")
    @ResponseBody
    public String testForm(@RequestParam("data")Model model){
        return model.toString();
    }

//



}
