package com.Maksim.SimpleToDo.controller;

import com.Maksim.SimpleToDo.entity.Note;
import com.Maksim.SimpleToDo.repos.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class NoteController {

    @Autowired
    NoteRepo noteRepo;

    @PostMapping("/addNote")
    public String addNote(Note note, @RequestParam("date") String date, @RequestParam("time") String time, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies)
            if (cookie.getName().equals("userId")) {
                cookies[0] = cookie;
                break;
            }
        note.setUserId(Long.parseLong(cookies[0].getValue()));
        if (!date.equals("") && !time.equals("")) {
            note.setEndTime(date + " " + time);
        }
        else if (!date.equals("") && time.equals("")) {
            note.setEndTime(date + " 24:00");
        }
        else if (date.equals("") && !time.equals("")) {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            Date dateNow = new Date();
            note.setEndTime(formater.format(dateNow) + " " + time);
        }

        noteRepo.save(note);
        return "redirect:/mainPage";
    }
}
