package com.Maksim.SimpleToDo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @RequestMapping("/")
    public String hello(){
        return "redirect:/";
    }

    @GetMapping("/")
    public String helloView(){
        return "helloPage.html";
    }

    @GetMapping("/signin")
    public String signinView(){
        return "signin.html";
    }

    @GetMapping("/signup")
    public String signupView(){
        return "signup.html";
    }

}
