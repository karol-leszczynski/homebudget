package pl.karoll.spring.homebudget.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
//    public String test

    @GetMapping("/test")
    @ResponseBody
    public String test() {

        return "Testowy Napis";
    }

}
