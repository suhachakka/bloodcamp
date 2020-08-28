package com.example.BloodCamp.controllers;

import com.example.BloodCamp.models.Donor;
import com.example.BloodCamp.models.Event;
import com.example.BloodCamp.models.Login;
import com.example.BloodCamp.models.data.DonorDao;
import com.example.BloodCamp.models.data.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller

@RequestMapping("donors")
public class DonorController {

    @Autowired
    private DonorDao donorDao;

    @Autowired
    private EventDao eventDao;

    @GetMapping
    public String viewdonor(Model model) {
        model.addAttribute("title","All donors");
        model.addAttribute("donors",donorDao.findAll());
        return "donors/view";
    }

    @RequestMapping(value="/")
    public String index(Model model){
        model.addAttribute("title","BLOOD CAMP");
        //model.addAttribute("users",donorDao.findAll());
        return "donors/welcome";
    }
    @GetMapping(value ="newdonor")
        public String displayNewDonorForm(Model model){
        model.addAttribute("title","NewDonor");
        model.addAttribute(new Donor());
        return "donors/newdonor";
    }


    @PostMapping(value="newdonor")
    public String processNewuserForm(@ModelAttribute @Valid Donor newDonor, Errors errors, Model model , HttpSession session){

        if(errors.hasErrors()){
            model.addAttribute("title","New Donor");
            return "donors/newdonor";
        }
        Donor existDonor = donorDao.findByEmail(newDonor.getEmail());
        Donor existDonorName = donorDao.findByName(newDonor.getName()) ;
        if(existDonor != null){
            return "redirect:/donor/newDonor?q=Donor+already+exists";

        }
        newDonor.setEvent((List<Event>) eventDao.findAll());
        donorDao.save(newDonor);
        model.addAttribute("title","All donors");
        model.addAttribute("donors", donorDao.findAll());
         session.setAttribute("donorname",newDonor.getName());
         session.setAttribute("sDonorId",newDonor.getId());
//        return "donors/view";
        return "redirect:";
    }
    @GetMapping(value="login")
    public String displayloginform(Model model){
        model.addAttribute("title","login");
        model.addAttribute(new Login());
        return "donors/login";
    }

    @PostMapping(value="login")
    public String processloginform(@ModelAttribute @Valid Login newLogin, Errors errors, Model model,HttpSession session){

        if(errors.hasErrors()){
            model.addAttribute("title","login");
            return "donors/login";
        }
        Donor matchDonor;
        matchDonor= donorDao.findByEmail(newLogin.getEmail());
        if(matchDonor != null && newLogin.getPassword().equals(matchDonor.getPassword())){
            session.setAttribute("donorname",matchDonor.getName());
            session.setAttribute("sDonorId",matchDonor.getId());
            session.setAttribute("role",matchDonor.getRole());
            return "donors/welcome";
        }
        model.addAttribute("message","Invalid Login");
        return "redirect:/donor/login?q=Invalid+Login";

    }

    @RequestMapping(value = "signout", method = RequestMethod.GET)
    public String processLogoutForm(HttpSession session) {
        session.removeAttribute("donorname");
        session.removeAttribute("sDonorId");
        return "donors/signout";
    }

    @GetMapping(value = "edit/{donorid}")
    public String displayEditForm(Model model,@PathVariable int donorid){
        Optional<Donor> donor = donorDao.findById(donorid);
        if(donor.isPresent()){
            model.addAttribute("donor",donor.get());
            model.addAttribute("title","editdonor");
           // model.addAttribute("users",donorDao.findAll());
        }
        return "donors/edit";
    }
    @PostMapping("edit")
    public String processEditForm(Donor donor, Model model,@RequestParam int donorid,@RequestParam String name,@RequestParam String bloodgroup,@RequestParam String mobilenumber,@RequestParam int zipcode,
                                  @RequestParam String  password, @RequestParam String confirmPassword) {
        Optional<Donor> optionalDonor = donorDao.findById(donorid);
        if (optionalDonor.isPresent()) {
            donor = optionalDonor.get();

            donor.setName(name);
            donor.setBloodgroup(bloodgroup);
            donor.setMobilenumber(mobilenumber);
            donor.setZipcode(zipcode);
            donor.setPassword(password);
            donor.setConfirmPassword(confirmPassword);
        }
          donorDao.save(donor);

        model.addAttribute("title","All donors");
        model.addAttribute("donors", donorDao.findAll());
        return "donors/view";
    }
    @GetMapping("delete")
    public String renderDeleteEventForm(Model model){
        model.addAttribute("donors",donorDao.findAll());

        return"donors/delete";
    }
    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] donorIds){
        if(donorIds != null){
            for(int id : donorIds){
                donorDao.deleteById(id);
            }
        }
//        return "donors/view";
        return "redirect:";


    }
    }
