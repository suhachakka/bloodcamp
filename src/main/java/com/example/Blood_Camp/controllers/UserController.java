package com.example.Blood_Camp.controllers;

import com.example.Blood_Camp.models.Donor;
import com.example.Blood_Camp.models.Login;
import com.example.Blood_Camp.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("user")
@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

   // @Autowired
   // private LoginDao loginDao;

    @RequestMapping(value="")
    public String index(Model model){
        model.addAttribute("title","BLOOD CAMP");
        model.addAttribute("users",userDao.findAll());
        return "user/welcome";
    }
    @GetMapping(value ="newdonor")
        public String displayNewDonorForm(Model model){
        model.addAttribute("title","NewDonor");
        model.addAttribute(new Donor());
        return "user/newdonor";
    }


    @PostMapping(value="newdonor")
    public String processNewuserForm(@ModelAttribute @Valid Donor newDonor, Errors errors, Model model ){

        if(errors.hasErrors()){
            model.addAttribute("title","New Donor");
            return "user/newdonor";
        }

        userDao.save(newDonor);
        return "user/welcome";
    }
    @GetMapping(value="login")
    public String displayloginform(Model model){
        model.addAttribute("title","login");
        model.addAttribute(new Login());
        return "user/login";
    }

    @PostMapping(value="login")
    public String processloginform(@ModelAttribute @Valid Login newLogin, Errors errors, Model model){

        if(errors.hasErrors()){
            model.addAttribute("title","login");
            return "user/login";
        }
        return "user/welcome";
    }

}
