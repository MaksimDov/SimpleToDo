package com.Maksim.SimpleToDo.controller;

import com.Maksim.SimpleToDo.entity.User;
import com.Maksim.SimpleToDo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RegistrationController {

    private static final Logger LOG = Logger.getLogger(RegistrationController.class.getName());
    @Autowired
    UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public String signup(User user){
        User userFromDB = userRepo.findByLogin(user.getLogin());
        if(userFromDB != null){
            LOG.info("User with this login already exists");
            return "redirect:/signup";
        }
        userFromDB = userRepo.findByEmail(user.getEmail());
        if(userFromDB != null){
            LOG.info("User with this email already exists");
            return "redirect:/signup";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);
        LOG.info("User: " + user.toString() + "registered");
        System.out.println(user);
        return "redirect:/signin";
    }

    @PostMapping("/signin")
    public String signin(User user, HttpServletResponse response){
        User userFromDB = userRepo.findByLoginOrEmail(user.getLogin(), user.getLogin());
        if(userFromDB == null){
            LOG.info("There is no user with this login or email");
            return "redirect:/signin";
        }

        if(passwordEncoder.matches(user.getPassword(), userFromDB.getPassword())){
            LOG.info("Wrong password for user: " + userFromDB.getLogin());
            return "redirect:/signin";
        }
        LOG.info("User " + user.getLogin() + " authorized.");
        Cookie cookie = new Cookie("userId", userFromDB.getId().toString());
        response.addCookie(cookie);
        return "redirect:/";
    }
}
