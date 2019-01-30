package pl.karoll.spring.homebudget.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class TestController {
//    public String test

    @GetMapping("/test")
    @ResponseBody
    public String test(Principal principal) {
        String name = principal.getName();
        return name;
    }

}
