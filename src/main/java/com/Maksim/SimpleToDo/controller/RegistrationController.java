package com.Maksim.SimpleToDo.controller;

import com.Maksim.SimpleToDo.entity.User;
import com.Maksim.SimpleToDo.repos.UserRepo;
import com.Maksim.SimpleToDo.service.MyPasswordEncoder;
import org.owasp.encoder.Encode;
import org.springframework.beans.factory.annotation.Autowired;
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


    @PostMapping("/signup")
    public String signup(User user) throws Exception {
        User userFromDB = userRepo.findByUsername(user.getUsername());
        if(userFromDB != null){
            LOG.info("User with this login already exists");
            return "redirect:/signup";
        }
        userFromDB = userRepo.findByEmail(user.getEmail());
        if(userFromDB != null){
            LOG.info("User with this email already exists");
            return "redirect:/signup";
        }
        user.setPassword(MyPasswordEncoder.getSaltedHash(user.getPassword()));
        userRepo.save(user);
        LOG.info("User: " + user.getUsername() + "registered");
        return "redirect:/signIn";
    }



    @PostMapping("/signIn")
    public String signin(User user, HttpServletResponse response) throws Exception {
//        User userFromDB = userRepo.findByLoginOrEmail(user.getLogin(), user.getLogin());
        User userFromDB = userRepo.findByUsername(user.getUsername());
        if(userFromDB == null){
            LOG.info("There is no user with this login or email");
            return "redirect:/signIn";
        }

        if(!MyPasswordEncoder.check(user.getPassword(), userFromDB.getPassword())){
            LOG.info("Wrong password for user: " + userFromDB.getUsername());
            return "redirect:/signIn";
        }
        LOG.info("User " + user.getUsername() + " authorized.");
        Cookie cookie = new Cookie("userId", userFromDB.getId().toString());
        response.addCookie(cookie);
        return "redirect:/mainPage";
    }
}
