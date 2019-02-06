package com.example.Blood_Camp.controllers;

import com.example.Blood_Camp.models.Login;
import com.example.Blood_Camp.models.User;
import com.example.Blood_Camp.models.data.LoginDao;
import com.example.Blood_Camp.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginDao loginDao;

    @RequestMapping(value="")
    public String index(Model model){
        model.addAttribute("title","BLOOD DONORS");
       // model.addAttribute(new User());
        return "index";
    }


    @RequestMapping(value="login", method=RequestMethod.GET)
    public String displayLogin(Model model){
        model.addAttribute(new User());
        return "login";
    }

    @RequestMapping(value="login", method = RequestMethod.POST)
    public String processLogin(@RequestParam("name") String name, @RequestParam("password") String password, Model model){

        Optional<Login> loginone = loginDao.findById(name);
        Login login = loginone.get();

        if(login == null){

            model.addAttribute("");
        }

        return "";
    }

    @RequestMapping(value="newdonor", method=RequestMethod.GET)
    public String displayNewuser(Model model){
        model.addAttribute( new User());
        return "newdonor";
    }

    @RequestMapping(value="newdonor", method=RequestMethod.POST)
    public String processNewuserForm(@ModelAttribute @Valid User newUser, Errors errors, Model model ){

        if(errors.hasErrors()){
            model.addAttribute("title","");
            return "newdonor";
        }

        Login login = new Login();
        login.setName(newUser.getName());
        login.setPassword(newUser.getPassword());
        userDao.save(newUser);
        loginDao.save(login);
        return "event";
    }

}
