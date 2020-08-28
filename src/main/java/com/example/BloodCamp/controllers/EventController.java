package com.example.BloodCamp.controllers;


import com.example.BloodCamp.models.Event;

import com.example.BloodCamp.models.data.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("events")

public class EventController {
    @Autowired
    private EventDao eventDao;


    @GetMapping("view/{id}")
    public String displayEvent(Model model, Event event, @PathVariable int id, HttpSession session) {
        model.addAttribute("title","Event Details");
//        model.addAttribute("events",eventDao.findAll());
        Optional<Event> optEvent = eventDao.findById(id);
        if(optEvent.isPresent())
            event = optEvent.get();
            model.addAttribute("events", event);

        return "events/view";
    }



    @GetMapping("create")
    public String displayEventForm(Model model) {
        model.addAttribute("title", " Create Event");
        model.addAttribute(new Event());
        return "events/create";


    }

    @PostMapping("create")
    public String processEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "events/create";
        }

        eventDao.save(newEvent);
        //System.out.println(newEvent.getId());

        return "redirect:view/" +newEvent.getId();
    }

    @GetMapping("recentEvent")
    public String displayRecentEvent(Model model){
        //$model.addAttribute("title", "Event Details");
        long eventSize = eventDao.count();

        //model.addAttribute("events",recentEvent.iterator().);
        //$return "events/view";
        return "redirect:view/" +String.valueOf(eventSize);

    }
}


