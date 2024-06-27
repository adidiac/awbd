package com.cafeteria.cafeteria.controllers.ViewControllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class HomeController {
    
    public HomeController() {
    }

    @GetMapping(value = {"/", "/home"})
    public String home() {
        return "home";
    }
}
