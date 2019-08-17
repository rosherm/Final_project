package org.launchcode.Final_Project.controllers;

import org.launchcode.Final_Project.models.data.userDao;
import org.launchcode.Final_Project.models.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller
@RequestMapping("tft")
public class indexController {

    @Autowired
    private userDao userDao;

    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("title", "TFT");
        return "index";
    }

    @RequestMapping(value = "champions", method = RequestMethod.GET)
    public String champions(Model model){
        model.addAttribute("title", "Champions");

        return "champions";
    }

    @RequestMapping(value = "items", method = RequestMethod.GET)
    public String items(Model model){
        model.addAttribute("title", "Items");

        return "items";
    }

    @RequestMapping(value = "stats", method = RequestMethod.GET)
    public String stats(Model model){
        model.addAttribute("title", "User Stats");

        return "stats";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("title", "User Register");
        model.addAttribute(new user());

        return "register";

    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String processRegister(@ModelAttribute  @Valid user newUser,
                                       Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "User Register");
            return "register";
        }

        userDao.save(newUser);

        return "stats";
    }




    @RequestMapping(value = "stats", method = RequestMethod.POST)
    public String processStats(@ModelAttribute @Valid user newUser,
                                  Errors errors, Model model){

        model.addAttribute("title", "User Page");

        if (errors.hasErrors()) {
            model.addAttribute("title", "User Register");
            return "register";
        }

        return "stats";
    }

}
