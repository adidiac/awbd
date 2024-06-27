package com.cafeteria.cafeteria.controllers.ViewControllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String login(@RequestParam(value = "error", required = false) String error) {
        return "auth/login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String register() {
        return "auth/register";
    }
    
}
