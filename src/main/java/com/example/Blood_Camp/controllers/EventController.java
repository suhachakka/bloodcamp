package com.example.Blood_Camp.controllers;


import com.example.Blood_Camp.models.Donor;
import com.example.Blood_Camp.models.Event;

import com.example.Blood_Camp.models.data.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("events")

public class EventController {
    @Autowired
    private EventDao eventDao;

    @GetMapping //path or route
    public String viewevent(Model model) { // route handler method
        model.addAttribute("title", "event details");
        model.addAttribute("events", eventDao.findAll());
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
//        model.addAttribute("title","All donors");
//        model.addAttribute("donors", donorDao.findAll());
        return "redirect:";

    }

    @GetMapping("view/{id}")
    public String renderDeleteEventForm(Model model) {
        model.addAttribute("events", eventDao.findAll());

        return "events/view";
    }

    @PostMapping("view")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {
        if (eventIds != null) {
            for (int id : eventIds) {
                eventDao.findById(id);
            }
        }
//        return "donors/view";
        return "redirect:";
    }
}