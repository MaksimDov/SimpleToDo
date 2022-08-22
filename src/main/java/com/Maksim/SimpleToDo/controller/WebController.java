package com.Maksim.SimpleToDo.controller;

import com.Maksim.SimpleToDo.repos.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {

    @Autowired
    NoteRepo noteRepo;
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
//        if(checkCookie(request))
//            return "redirect:/mainPage";
        return "signin.html";
    }

    @GetMapping("/signup")
    public String signupView(HttpServletRequest request){
//        if(checkCookie(request))
//            return "redirect:/mainPage";
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

    @RequestMapping("/mainPage/{noteId}")
    public String singleNote(@PathVariable(value = "noteId") Long noteId) {
        if (noteRepo.findById(noteId).isPresent()) {
            return "singleNote.html";
        } else {
            return "redirect:/mainPage";
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
