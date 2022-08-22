package com.Maksim.SimpleToDo.controller;

import com.Maksim.SimpleToDo.entity.Note;
import com.Maksim.SimpleToDo.exception.NoEntityException;
import com.Maksim.SimpleToDo.repos.NoteRepo;
import com.Maksim.SimpleToDo.repos.UserRepo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mainPage")
public class ViewNotesController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    NoteRepo noteRepo;

    @GetMapping("/update")
    public String viewAllNotes(HttpServletRequest request) {
        if (noteRepo.findAll() == null)
            return "";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies)
            if (cookie.getName().equals("userId")) {
                cookies[0] = cookie;
                break;
            }
        List<Note> notes = noteRepo.findAllByUserId(Long.parseLong(cookies[0].getValue())).stream().sorted(Comparator.comparing(Note::getId)).collect(Collectors.toList());

        JSONArray resultJson = new JSONArray();
        for (Note note : notes) {
            JSONObject tempJson = new JSONObject();
            tempJson.put("noteId", note.getId());
            tempJson.put("noteName", note.getName());
            tempJson.put("noteDescription", note.getDescription());
            tempJson.put("noteType", note.getType());
            tempJson.put("noteEndTime", note.getEndTime());
            resultJson.add(tempJson);
        }
        return resultJson.toString();
    }

    @GetMapping("/{noteId}/watchNote")
    public String watchAd(@PathVariable("noteId") Long noteId) {
        if (noteRepo.findById(noteId).isEmpty())
            return "This advert has been deleted.";
        return "/mainPage/" + noteId;
    }

    @GetMapping("/{noteId}/updateSingleNote")
    public String viewSingleAd(@PathVariable("noteId") Long noteId) throws NoEntityException {
        Note note = noteRepo.findById(noteId).orElseThrow(() -> new NoEntityException(noteId));
        JSONObject resultJson = new JSONObject();
        resultJson.put("noteId", note.getId());
        resultJson.put("noteName", note.getName());
        resultJson.put("noteDescription", note.getDescription());
        resultJson.put("noteType", note.getType());
        resultJson.put("noteEndTime", note.getEndTime());
        resultJson.put("noteIsDone", note.isDoneMark());
        return resultJson.toString();
    }

    @GetMapping("/{noteId}/changeIsDoneMark")
    public String changePic(@PathVariable("noteId") Long noteId) throws NoEntityException {
        Note note = noteRepo.findById(noteId).orElseThrow(() -> new NoEntityException(noteId));
        if (note.isDoneMark()) {
            noteRepo.updateDoneMark(false, noteId);
        }
        else {
            noteRepo.updateDoneMark(true, noteId);
        }
        JSONObject resultJson = new JSONObject();
        resultJson.put("doneMark", "true");
        resultJson.put("noteId", noteId);
        return resultJson.toString();
    }
}
