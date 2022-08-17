package com.Maksim.SimpleToDo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Controller
public class WebController {
    @RequestMapping("/")
    public String hello(){
        return "redirect:/";
    }

    @GetMapping("/")
    public String helloView(){
        Date date = new Date();
        return "helloPage.html";
    }

    @GetMapping("/signIn")
    public String signinView(){
        return "signin.html";
    }

    @GetMapping("/signup")
    public String signupView(){
        return "signup.html";
    }

    @GetMapping("/mainPage")
    public String mainPageView(){
        return "mainPage.html";
    }

    @GetMapping("/addNote")
    public String addNoteView(){
        return "addNewNote.html";
    }
}
