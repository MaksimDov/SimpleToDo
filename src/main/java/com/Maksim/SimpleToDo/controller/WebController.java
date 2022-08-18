package com.Maksim.SimpleToDo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/signIn")
    public String signinView(HttpServletRequest request){
        if(checkCookie(request))
            return "redirect:/mainPage";
        return "signin.html";
    }

    @GetMapping("/signup")
    public String signupView(HttpServletRequest request){
        if(checkCookie(request))
            return "redirect:/mainPage";
        return "signup.html";
    }

    @GetMapping("/mainPage")
    public String mainPageView(HttpServletRequest request){
        if(checkCookie(request)) {
            return "mainPage.html";
        }
        else {
            return "redirect:/signIn";
        }
    }

    @GetMapping("/addNote")
    public String addNoteView(HttpServletRequest request){
        if(checkCookie(request)) {
            return "addNewNote.html";
        }
        else {
            return "redirect:/signIn";
        }
    }

    private static boolean checkCookie(HttpServletRequest request){
        boolean isAuthorize = false;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userId")) {
                if (!cookie.getValue().equals("")) {
                    isAuthorize = true;
                }
                break;
            }
        }
        return isAuthorize;
    }
}
